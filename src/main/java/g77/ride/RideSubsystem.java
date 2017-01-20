package g77.ride;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import g77.common.entities.Driver;
import g77.common.entities.Location;
import g77.common.entities.Passenger;
import g77.common.entities.RideRequest;
import g77.ride.fare.PrimeFareCalculator;
import g77.ride.fare.RegularFareCalculator;
import g77.ride.fare.RideFareCalculator;
import g77.ride.route.Route;
import g77.ride.route.RoutingStrategy;

//singleton
public class RideSubsystem {

	private static RideSubsystem rideSubsystem = new RideSubsystem();;
	private ConcurrentLinkedQueue<Ride> requestedRides = new ConcurrentLinkedQueue<Ride>();
	private ConcurrentLinkedQueue<Ride> inProgressRides = new ConcurrentLinkedQueue<Ride>();
	private ConcurrentLinkedQueue<Ride> completedRides = new ConcurrentLinkedQueue<Ride>();
	private ConcurrentLinkedQueue<DriverLocation> drivers = new ConcurrentLinkedQueue<DriverLocation>();
	//private Object object = new Object();
		
	private RideScheduler rideScheduler= new RideScheduler();
	private RideTracker rideTracker = new RideTracker();
	
	private RideSubsystem(){

		//Start the RideScheduler
		rideScheduler.start();
		//Start the RideTracker
		rideTracker.start();		
	}
		
	public static RideSubsystem  getRideSubSystem(){
		
		return rideSubsystem;
	}
		
	
	public ConcurrentLinkedQueue<Ride> getRequestedRides() {
		return requestedRides;
	}

	public void setRequestedRides(ConcurrentLinkedQueue<Ride> requestedRides) {
		this.requestedRides = requestedRides;
	}

	public ConcurrentLinkedQueue<Ride> getInProgressRides() {
		return inProgressRides;
	}

	public void setInProgressRides(ConcurrentLinkedQueue<Ride> inProgressRides) {
		this.inProgressRides = inProgressRides;
	}

	public ConcurrentLinkedQueue<Ride> getCompletedRides() {
		return completedRides;
	}

	public void setCompletedRides(ConcurrentLinkedQueue<Ride> completedRides) {
		this.completedRides = completedRides;
	}
	
	public void  moveConfirmRideToInProgress(Ride ride){
		
			synchronized(inProgressRides){
					inProgressRides.add(ride);
				}
			synchronized(requestedRides){
				requestedRides.remove(ride);
			}	
	}

	public void  moveInProgressRideToCompleted(Ride ride){
		
			synchronized(completedRides){
				completedRides.add(ride);
			}
			synchronized(inProgressRides){
				inProgressRides.remove(ride);
			}
		
	}	
	
	//To handle cancellation
	public void  moveRequestedRideToCompleted(Ride ride){
		
		synchronized(completedRides){
			completedRides.add(ride);
			}
		synchronized(requestedRides){
			requestedRides.remove(ride);
		}	
	}	
	
	//To handle cancellation
	public void  moveConfirmedRideToCompleted(Ride ride){
		
		synchronized(completedRides){
			completedRides.add(ride);
			}
		synchronized(inProgressRides){
			requestedRides.remove(ride);
		}	
	}	
	
	
	public boolean addDrivertoTrack(Driver driver){
		
		DriverLocation ds = new DriverLocation();
		ds.setDriver(driver);
		ds.setCurrentLocation(new Location(0,0));
		ds.setDriverStatus(DriverStatus.ONLINE);
		
		synchronized(drivers){
			drivers.add(ds);
		}
		return true;
		
	}
	
	public boolean removeDrivertoTrack(Driver driver){
			
		Iterator<DriverLocation> iterator = drivers.iterator();
		
		while(iterator.hasNext()) {
			
			DriverLocation driverLocation = iterator.next();
			
			synchronized(drivers){	
				if(driverLocation.getDriver().getName().equals(driver.getName())){
					System.out.println("Remove Driver from Tracking- "+ driverLocation.getDriver().getName() );
					return true;
				}
			}
		}
		return true;
		
	}	
	
	
	public Driver getNearbyDriver(Location location){
		
		//Find the near by driver based on driver status and location
		synchronized(drivers){
			Iterator<DriverLocation> iterator = drivers.iterator();
			
			while(iterator.hasNext()) {
				
			        DriverLocation driverStatus = iterator.next();
			        
			        Driver d = driverStatus.getDriver();
			        Location loc = driverStatus.getCurrentLocation();	        
			        
			        if(driverStatus.getDriverStatus() == DriverStatus.ONLINE){				        
				        if(loc.isNearbyAndOnline(location)){
				        	return d;
				        }
			        }
			}
		}
		return null;	
	}
	
	public boolean ChangeDriverState(Driver driver, DriverStatus driverStatus){
		
		synchronized(drivers){
			Iterator<DriverLocation> iterator = drivers.iterator();
			while(iterator.hasNext()) {
				DriverLocation driverLocation = iterator.next();
				if(driverLocation.getDriver().getName().equals(driver.getName())){
					driverLocation.setDriverStatus(driverStatus);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isDriverBusy(Driver driver){
		
		
		synchronized(drivers){
			
			Iterator<DriverLocation> iterator = drivers.iterator();
			
			while(iterator.hasNext()) {
				
				DriverLocation driverLocation = iterator.next();
				if(driverLocation.getDriver().getName().equals(driver.getName())){
					
					if(driverLocation.getDriverStatus() == DriverStatus.BUSY){
						System.out.println("Driver State is BUSY -" + driver);
						return true;
					}else{
						return false;
					}			
				}
			}
		}
		return false;
	}
	
	public boolean acceptRide(Driver driver){
		
	
			Iterator<DriverLocation> iterator = drivers.iterator();
			
			while(iterator.hasNext()) {
				
				DriverLocation driverLocation = iterator.next();
				
				System.out.println("acceptRide processing Driver - "+ driverLocation.getDriver().getName() );
				
				synchronized(drivers){	
					if(driverLocation.getDriver().getName().equals(driver.getName())){
						driverLocation.setDriverStatus(DriverStatus.BUSY);
						System.out.println("Driver State set to BUSY");
						return true;
					}
				}
			}
		return false;
	}
	
	public boolean RideStart(Driver driver){
		
		synchronized(drivers){		
			Iterator<DriverLocation> iterator = drivers.iterator();
			
			while(iterator.hasNext()) {
				
				DriverLocation driverLocation = iterator.next();
				
				System.out.println("Ride Started by Driver - "+ driverLocation.getDriver().getName() );
				
				if(driverLocation.getDriver().getName().equals(driver.getName())){
					
					
					synchronized(inProgressRides){
						
						Iterator<Ride> iteratorRide = inProgressRides.iterator();
						
						while(iteratorRide.hasNext()){
							
							Ride ride = iteratorRide.next();
							if(driverLocation.getDriver().getName().equals(ride.getDriver().getName())){
								
								ride.StartRide();
							}
						}
					}
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean RideCompleted(Driver driver){
		
		synchronized(drivers){		
			Iterator<DriverLocation> iterator = drivers.iterator();
			
			while(iterator.hasNext()) {
				
				DriverLocation driverLocation = iterator.next();
				
				System.out.println("Ride Mark Done by Driver - "+ driverLocation.getDriver().getName() );
				
				if(driverLocation.getDriver().getName().equals(driver.getName())){
					
					Iterator<Ride> iteratorRide = inProgressRides.iterator();
					
					while(iteratorRide.hasNext()){
						
						synchronized(inProgressRides){
							Ride ride = iteratorRide.next();
							if(driverLocation.getDriver().getName().equals(ride.getDriver().getName())){
								
								ride.CompleteRide();
							}
						}
					}
					
					driverLocation.setDriverStatus(DriverStatus.ONLINE);
					System.out.println("Driver State set to ONLINE");
					return true;
				}
			}
		}
		
		return true;
	}	
	
	public boolean RideCancelledbyDriver(Driver driver){
		
		boolean ridecancelled=false;
		
		synchronized(drivers){		
			
			Iterator<DriverLocation> iterator = drivers.iterator();
			
			while(iterator.hasNext()) {
				
				DriverLocation driverLocation = iterator.next();
				
				System.out.println("Ride Cancelled by Driver - "+ driverLocation.getDriver().getName() );
				
				if(driverLocation.getDriver().getName().equals(driver.getName())){
					
					Iterator<Ride> iteratorRide = inProgressRides.iterator();
					
					while(iteratorRide.hasNext()){
						
						synchronized(inProgressRides){
							Ride ride = iteratorRide.next();
							if(driverLocation.getDriver().getName().equals(ride.getDriver().getName())){
								if(ride.getRideState().getRideState().equals("RITE_STATE_CONFIRMED")){
									ride.RideCancel();
									ridecancelled=true;
								}
							}
						}
					}
					if(ridecancelled){
						driverLocation.setDriverStatus(DriverStatus.ONLINE);
						System.out.println("Driver State set to ONLINE");
						return true;
					}
				}
			}
		}
		return ridecancelled;
	}
	
	public UUID requestRide(RideRequest rideRequest){

		//Check the ride request and decide what type of ride it is.
		Ride ride = new SimpleRide(rideRequest);
		
		UUID uuid = UUID.randomUUID();
		
		ride.setRideId(uuid);
		
		//Update location. - TODO
		//ride.getRideRequest().getStartLocation().getCity();
		ride.getRideRequest().getStartLocation().updateLocation(0, 0);
		
		
		//Calculate route 
		
		Route route = ride.CalculateRoute();
		
		
		//Calculate estimated fare 
		RideFareCalculator rideFareCalculator = null;
		if(ride.getRideRequest().getStartTime().getHours() > 21 || ride.getRideRequest().getStartTime().getHours() < 6 ){
			rideFareCalculator= new PrimeFareCalculator(ride);
		}else{
			rideFareCalculator = new RegularFareCalculator(ride);
		}
		ride.setEstimatedFare(rideFareCalculator.CalculateFare());

		synchronized(requestedRides){
			RideSubsystem.getRideSubSystem().getRequestedRides().add(ride);
		}
		System.out.println("RIDE REQUEST ACCEPTED - Waiting for Driver to Confirm" + ride );
		
		return uuid;
		
	}
	
	public boolean addTip(UUID rideId, Passenger passenger, int tipAmount){
		
		//tip can be added to payment due ride only.
		Iterator<Ride> iterator = RideSubsystem.getRideSubSystem().getInProgressRides().iterator();
		
		while(iterator.hasNext()) {
			Ride ride = iterator.next();
			synchronized(RideSubsystem.getRideSubSystem().getInProgressRides()){
				if(ride.getRideId() ==  rideId && ride.getRideState().getRideState().equals(RideState.rideStatePaymentDue)){
					ride.setTipAmount(tipAmount);
					System.out.println("PAYMENT - TIP ADDED to Ride " + ride);
				}
			}
		}
		
		return true;
	}
	
	public boolean RideCancelledbyPassenger(UUID rideId, Passenger passenger){
		
		boolean rideCancelled=false;
		
		//check if requested rides contains the ride
		if(!rideCancelled){
			Iterator<Ride> iterator = RideSubsystem.getRideSubSystem().getRequestedRides().iterator();
			while(iterator.hasNext()) {
				Ride ride = iterator.next();
				synchronized(RideSubsystem.getRideSubSystem().getRequestedRides()){
					if(ride.getRideId() ==  rideId){
						System.out.println("RIDE CANCELLED by Passenger - "+ passenger.getName() );
						ride.RideCancel();
						rideCancelled=true;
					}
				}
			}
		}
		
		if(!rideCancelled){
			Iterator<Ride> iterator = RideSubsystem.getRideSubSystem().getInProgressRides().iterator();
			while(iterator.hasNext()) {
				Ride ride = iterator.next();
				synchronized(RideSubsystem.getRideSubSystem().getInProgressRides()){
					if(ride.getRideId() ==  rideId && ride.getRideState().getRideState().equals("RITE_STATE_CONFIRMED")){
						ride.RideCancel();
						rideCancelled=true;
					}
				}
			}
		}
		
		if(rideCancelled){
			System.out.println("Ride Cancel - Successfully Cancelled by Passenger !!!" );
		}else{
			System.out.println("Ride  Cancel - Unable to cancel the ride !!!" );
		}
		
		return rideCancelled;
	}
	

}
