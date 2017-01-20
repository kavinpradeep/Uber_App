package g77.ride;

import g77.common.entities.RideRequest;


public class PoolRide extends Ride{
	
	PoolRide(RideRequest rideRequest) {
		super(rideRequest);
		// TODO Auto-generated constructor stub
	}
	
	Ride driverRide;
	Ride passengerRide;
}
