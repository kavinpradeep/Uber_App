package g77.ride;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import g77.common.entities.Driver;
import g77.common.entities.PaymentPreference;
import g77.common.entities.PaymentType;
import g77.common.entities.RideRequest;
import g77.notification.NotificationManager;
import g77.payment.PaybyCard;
import g77.payment.PaymentMode;
import g77.payment.RidePayment;
import g77.ride.fare.RegularFareCalculator;
import g77.ride.route.PeakHourRoute;
import g77.ride.route.RegularHourRoute;
import g77.ride.route.Route;
import g77.ride.route.RoutingStrategy;

public abstract class Ride {
	
	private RideRequest rideRequest;
	private UUID RideId;
	private Driver driver;
	private int ETA;
	private int EstimatedFare;
	private RoutingStrategy routingStrategy;	
	private Route calculatedRoute;
	private RidePayment ridePayment;
	
	//Updated by the tracker while ride is in progress.
	private Route actualRoute;
	private int actualFare;
	private int tipAmount;
	
	//Ride state changed - update the observers.
	private RideState rideState;
	private List<IRideStateObserver> rideStateObservers = new ArrayList<IRideStateObserver>();
	
	
	//Observer pattern methods - 
	public void addRideStateObserver(IRideStateObserver rideStateObserver){
		rideStateObservers.add(rideStateObserver);
	}
	public void removeRideStateObserver(IRideStateObserver rideStateObserver){
		
		for(Iterator<IRideStateObserver> rideStateObserverIter = rideStateObservers.listIterator(); rideStateObserverIter.hasNext();){
			IRideStateObserver tempRideStateObserver = rideStateObserverIter.next();
			if(tempRideStateObserver == rideStateObserver){
				rideStateObserverIter.remove();
				break;
			}
		}
	}
	public void notifyRideStateChanged(RideState previousRideState, RideState currentRideState){
		
		for(Iterator<IRideStateObserver> rideStateObserverIter = rideStateObservers.listIterator(); rideStateObserverIter.hasNext();){
			IRideStateObserver tempRideStateObserver = rideStateObserverIter.next();
			tempRideStateObserver.RideStateChanged(this, previousRideState, currentRideState);
		}
	}
	
	
	public Ride(){
		
	 }
	
	public Ride(RideRequest rideRequest){
		this.rideRequest = rideRequest;
		this.setRoutingStrategy();
		this.setRidePayment();
		this.setRideState(new RideStateRequested(this));
		this.setActualFare(0);
		this.setTipAmount(0);
		addRideStateObserver(NotificationManager.getNotificationManager());
		
	}
	

	//Ride - State Pattern methods
	public boolean ConfirmRide(){
		return rideState.ConfirmRide();
	}
	
	public boolean StartRide(){
		return rideState.StartRide();
	}
	
	public boolean CompleteRide(){
		return rideState.CompleteRide();
	}
	
	public boolean RideCancel(){
		return rideState.RideCancel();
	}
	
	public boolean ProcessPayment(){
		return rideState.ProcessPayment();
	}
	
	
	//Ride Strategy Pattern methods
	private boolean setRoutingStrategy(){
		
		if((this.getRideRequest().getStartTime().getHours() > 7 && this.getRideRequest().getStartTime().getHours() < 9) && 
				(this.getRideRequest().getStartTime().getHours() > 16 && this.getRideRequest().getStartTime().getHours() < 18)){
			routingStrategy = new PeakHourRoute();
		}else{
			routingStrategy = new RegularHourRoute();
		}
		
		return true;
	}
	
	public Route CalculateRoute(){
		
		calculatedRoute = routingStrategy.CalculateRoute(this);
		return calculatedRoute;
		
	}
	
	//getter and setter methods 
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public int getETA() {
		return ETA;
	}

	public void setETA(int ETA) {
		this.ETA = ETA;
	}

	public int getEstimatedFare() {
		return EstimatedFare;
	}

	public void setEstimatedFare(int estimatedFare) {
		EstimatedFare = estimatedFare;
	}

	public RideState getRideState() {
		return rideState;
	}

	public void setRideState(RideState rideState) {
		RideState previousRideState = this.rideState;
		this.rideState = rideState;
		this.notifyRideStateChanged(previousRideState, this.rideState);
		
	}
	
	public RideRequest getRideRequest() {
		return rideRequest;
	}

	public void setRideRequest(RideRequest rideRequest) {
		this.rideRequest = rideRequest;
	}

	public int getActualFare() {
		return actualFare;
	}

	public void setActualFare(int actualFare) {
		this.actualFare = actualFare;
	}

	public Route getActualRoute() {
		return actualRoute;
	}

	public void setActualRoute(Route actualRoute) {
		this.actualRoute = actualRoute;
	}
	
	public boolean setRidePayment(){
		
		PaymentPreference paymentPreference = this.getRideRequest().getPassenger().getPaymentPreference();
		PaymentMode paymentMode = null;
		
		if(this.getRideRequest().getPassenger().getPaymentPreference().getPaymentType() == PaymentType.PAYBYCARD){
			 paymentMode = new PaybyCard(paymentPreference.getName(),paymentPreference.getCardNumber(),paymentPreference.getCVCNumber(),paymentPreference.getValidTill());
			
		}else{
			//todo add paybypaypal 
		}
		
		ridePayment = new RidePayment(paymentMode);
		return true;
	}
	
	public RidePayment getRidePayment() {
		return ridePayment;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rideRequest == null) ? 0 : rideRequest.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ride other = (Ride) obj;
		if (rideRequest == null) {
			if (other.rideRequest != null)
				return false;
		} else if (!rideRequest.equals(other.rideRequest))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Ride [RideId=" + RideId + ",rideState=" + rideState  +",rideRequest=" + rideRequest + ", driver=" + driver + "]";
	}
	
	public int getTipAmount() {
		return tipAmount;
	}
	
	public void setTipAmount(int tipAmount) {
		this.tipAmount = tipAmount;
	}
	public UUID getRideId() {
		return RideId;
	}
	public void setRideId(UUID rideId) {
		RideId = rideId;
	}
		
	
}
