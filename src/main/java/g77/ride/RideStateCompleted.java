package g77.ride;

public class RideStateCompleted implements RideState{

	private Ride ride;
	
	public boolean AcceptRide() {
		// TODO Auto-generated method stub
		return false;
	}
	
	RideStateCompleted(Ride ride){
		this.ride = ride;
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
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean CompleteRide() {
		// TODO Auto-generated method stub
		return false;
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
		return "RITE_STATE_COMPLETED";
	}

	@Override
	public String toString() {
		return " [RideState:" +getRideState() + "]";
	}

	

}
