package g77.ride.fare;

import g77.ride.Ride;

public class FreeFareCalculator extends RideFareCalculator{

	public FreeFareCalculator(Ride ride) {
		super(ride);
		
	}

	@Override
	public int CalculateFare() {
		
		return 0;
	}

}
