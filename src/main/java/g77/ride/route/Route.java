package g77.ride.route;

import java.util.ArrayList;
import java.util.List;

import g77.common.entities.Location;

public class Route {
	
	private List<Location> locationList = new ArrayList<Location>();
	
	private int Distance;

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public int getDistance() {
		return Distance;
	}

	public void setDistance(int distance) {
		Distance = distance;
	}
	
}

