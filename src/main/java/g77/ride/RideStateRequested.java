package g77.ride;

import g77.common.entities.Driver;
import g77.payment.RidePayment;
import g77.ride.fare.PrimeFareCalculator;
import g77.ride.fare.RegularFareCalculator;
import g77.ride.fare.RideFareCalculator;

public class RideStateRequested implements RideState{

	private Ride ride;
	
	RideStateRequested(Ride ride){
		this.ride = ride;		
	}
	
	public boolean AcceptRide() {
		return false;
	}	
		
	public boolean ConfirmRide() {
		
		System.out.println("Ride - Driver Accepted for " + ride.toString());
		System.out.println("Driver Details : " + ride.getDriver().getName());
		//Hold payment 
		ride.getRidePayment().holdPayment(ride.getEstimatedFare());
		
		System.out.println("Ride Confirmed - Ride State Changed to RideStateConfirmed");	
		
		RideSubsystem.getRideSubSystem().moveConfirmRideToInProgress(ride);
		
		ride.setRideState(new  RideStateConfirmed(ride));

		return true;
	}

	public boolean StartRide() {
		
		return false;
	}

	public boolean TrackAndUpdateRide() {
		
		return false;
	}	
	
	public boolean CompleteRide() {
		
		return false;
	}

	public boolean RideCancel() {
		
		RideSubsystem.getRideSubSystem().moveRequestedRideToCompleted(ride);
		ride.setRideState(new  RideStateCompleted(ride));
		System.out.println("Ride moved to completed State due to Cancel = " + ride);
		return true;
	}

	public boolean ProcessPayment() {
		
		return false;
	}

	public String getRideState() {
		return "RITE_STATE_REQUESTED";
	}
	
	@Override
	public String toString() {
		return " [RideState:" +getRideState() + "]";
	}
	
}
