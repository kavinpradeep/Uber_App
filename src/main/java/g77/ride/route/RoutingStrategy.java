package g77.ride.route;

import g77.ride.Ride;

public interface RoutingStrategy {

	public Route CalculateRoute(Ride ride);
	
}
