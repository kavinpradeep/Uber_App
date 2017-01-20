package g77.ride;

public class RideStatePaymentDue implements RideState{

	private Ride ride;
	
	RideStatePaymentDue(Ride ride){
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
		// TODO Auto-generated method stub
		return false;
	}	
	
	public boolean CompleteRide() {
		
		//If the payment is complete, move the state to completed
		
		return false;
	}

	public boolean RideCancel() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean ProcessPayment() {
		
		
		ride.getRidePayment().releaseholdPayment();
		System.out.println("Actual amount:"+ ride.getActualFare());
		System.out.println("Tip amount:"+ ride.getTipAmount());
		ride.getRidePayment().setRideFare(ride.getActualFare(), ride.getTipAmount());
		ride.getRidePayment().makepayment();
		
		System.out.println("Complete Ride");
		
		//TODO - move the ride to completed list
		
		RideSubsystem.getRideSubSystem().moveInProgressRideToCompleted(ride);;
		
		System.out.println("Ride moved to completed state after Complete = " + ride);
		ride.setRideState(new RideStateCompleted(ride));	
		return true;
	}

	public String getRideState() {
		return "RITE_STATE_PAYMENTDUE";
	}
	
	@Override
	public String toString() {
		return " [RideState:" +getRideState() + "]";
	}
}
