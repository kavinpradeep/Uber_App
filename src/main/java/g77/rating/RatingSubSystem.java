package g77.rating;

import g77.ride.IRideStateObserver;
import g77.ride.Ride;
import g77.ride.RideState;

public class RatingSubSystem implements IRideStateObserver{

	public void RideStateChanged(Ride ride, RideState previousState, RideState CurrentState) {
		//TODO - Design Completed. - To be Implemented
		//If the ride state is completed - then we could ask User for Rating. 
	}
	
}
