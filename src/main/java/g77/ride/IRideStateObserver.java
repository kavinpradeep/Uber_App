package g77.ride;

public interface IRideStateObserver {
		public void RideStateChanged(Ride ride, RideState previousState,RideState CurrentState);
}
