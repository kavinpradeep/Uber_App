package g77.ride;

import g77.ride.fare.PrimeFareCalculator;
import g77.ride.fare.RegularFareCalculator;
import g77.ride.fare.RideFareCalculator;

public class RideStateInProgress implements RideState{

	
	private Ride ride;
	
	RideStateInProgress(Ride ride){
		this.ride = ride;		
	}
	
	public boolean AcceptRide() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean ConfirmRide() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean StartRide() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean TrackAndUpdateRide() {
		
		//get the driver current location and update the ride's current details of how far travelled, remaining distance and time to reach destination
	
		// is the ride completed flag set by driver - if so, move the ride to Payment Due State
		// create RidePayment and call pay 
		// move the state to payment due
		
		
		return false;
	}	
	
	public boolean CompleteRide() {
		
		System.out.println("Complete Ride");
		
		RideFareCalculator rideFareCalculator = null;
		
		if(ride.getRideRequest().getStartTime().getHours() > 21 || ride.getRideRequest().getStartTime().getHours() < 6 ){
			rideFareCalculator= new PrimeFareCalculator(ride);
		}else{
			rideFareCalculator = new RegularFareCalculator(ride);
		}
		ride.setActualFare(rideFareCalculator.CalculateFare());
		
		ride.setRideState(new RideStatePaymentDue(ride));
		
		return true;
	}

	public boolean RideCancel() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean ProcessPayment() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getRideState() {
		return "RITE_STATE_INPROGRESS";
	}
	
	@Override
	public String toString() {
		return " [RideState:" +getRideState() + "]";
	}
	
	
}
