package g77.ride.route;

import g77.common.entities.Location;
import g77.ride.Ride;

public class PeakHourRoute implements RoutingStrategy {

	public Route CalculateRoute(Ride ride) {

		Route route = new Route();	
		MapsService svc = new MapsService();
		route = svc.getRoute(2,ride.getRideRequest().getStartLocation().getCity(),ride.getRideRequest().getEndLocation().getCity());
		System.out.println("Peak Hour Route - Calculate Route: Distance:" + route.getDistance() + " miles, LocationList:"+ route.getLocationList());
		return route;		
		
	}	
	
}
