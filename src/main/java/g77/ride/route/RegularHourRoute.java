package g77.ride.route;

import g77.common.entities.Location;
import g77.ride.Ride;

public class RegularHourRoute implements RoutingStrategy {

	public Route CalculateRoute(Ride ride) {
		
		
		Route route;
		MapsService svc = new MapsService();
		route = svc.getRoute(1,ride.getRideRequest().getStartLocation().getCity(),ride.getRideRequest().getEndLocation().getCity());
		System.out.println("Regular Hour Route - Calculate Route: Distance:" + route.getDistance() + " miles, LocationList:"+ route.getLocationList());
		return route;
		
	}
}
