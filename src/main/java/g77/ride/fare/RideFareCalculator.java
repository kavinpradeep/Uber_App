package g77.ride.fare;

import g77.ride.Ride;

public abstract class RideFareCalculator extends Ride {

	protected Ride ride;
	
	public RideFareCalculator(Ride ride){
		this.ride = ride;
	}
	
	public abstract int CalculateFare();
	
}
