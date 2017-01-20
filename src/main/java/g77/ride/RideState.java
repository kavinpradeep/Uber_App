package g77.ride;

public interface RideState {
	
		public static final String rideStateRequested = "RITE_STATE_REQUESTED";
		public static final String rideStateConfirmed = "RITE_STATE_CONFIRMED";
		public static final String rideStateInProgress = "RITE_STATE_INPROGRESS";
		public static final String rideStateCompleted = "RITE_STATE_COMPLETED";
		public static final String rideStatePaymentDue = "RITE_STATE_PAYMENTDUE";
		
		public String getRideState();
		public boolean AcceptRide();
		public boolean ConfirmRide();
		public boolean StartRide();
		public boolean TrackAndUpdateRide();
		public boolean CompleteRide();
		public boolean RideCancel();
		public boolean ProcessPayment();		
		
		
}
