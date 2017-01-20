package g77.ride.fare;

import g77.ride.Ride;

public class RegularFareCalculator extends RideFareCalculator {

	public RegularFareCalculator(Ride ride) {
		super(ride);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int CalculateFare() {
		
		int fare = ride.CalculateRoute().getDistance()*FareCriteria.FARE_PER_MILE;
		return fare;
	}

}
