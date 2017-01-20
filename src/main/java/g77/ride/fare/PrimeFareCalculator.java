package g77.ride.fare;

import g77.ride.Ride;

public class PrimeFareCalculator extends RideFareCalculator{
	
	
	public PrimeFareCalculator(Ride ride){
		
		super(ride);
	}
	
	@Override
	public int CalculateFare() {
		// TODO call the fareManager and provide relevant details and get the fare.
		int fare = ride.CalculateRoute().getDistance()*FareCriteria.FARE_PER_MILE * FareCriteria.PEAK_HOUR_TIMES;
		return fare;

	}
	
}
