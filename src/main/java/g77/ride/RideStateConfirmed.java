package g77.ride;

public class RideStateConfirmed implements RideState{

	
	private Ride ride;
	
	RideStateConfirmed(Ride ride){
		this.ride = ride;		
	}
	
	public boolean AcceptRide() {
		// TODO Auto-generated method stub
		return false;
	}	

	public boolean ConfirmRide() {	
		return false;
	}

	public boolean StartRide() {
			
		//When driver mark the ride start, Change the ride state to in-progress

		// Notify Passenger  about ride started		
		System.out.println("StartRide");
		ride.setRideState(new RideStateInProgress(ride));
		return false;
	}

	public boolean TrackAndUpdateRide() {
		
		//get the driver current location and update the ride's driver ETA to reach passenger
		return false;
	}	
	
	public boolean CompleteRide() {

		return false;
	}

	public boolean RideCancel() {
		RideSubsystem.getRideSubSystem().moveInProgressRideToCompleted(ride);
		ride.setRideState(new  RideStateCompleted(ride));
		
		System.out.println("Ride moved to completed State due to Cancel = " + ride);
		return true;
	}

	public boolean ProcessPayment() {

		return false;
	}

	public String getRideState() {
		return "RITE_STATE_CONFIRMED";
	}
	
	@Override
	public String toString() {
		return " [RideState:" +getRideState() + "]";
	}
}
