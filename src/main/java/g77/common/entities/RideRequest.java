package g77.common.entities;

import java.util.Date;

public class RideRequest {

	private Date startTime;
	private int NumofPassengers;
	private Location startLocation;
	private Location endLocation;
	
	private Passenger passenger;
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Location getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Location startLocation) {
		this.startLocation = startLocation;
	}

	public Passenger getPassenger() {
		return passenger;
	}


	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public Location getEndLocation() {
		return endLocation;
	}


	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endLocation == null) ? 0 : endLocation.hashCode());
		result = prime * result + ((passenger == null) ? 0 : passenger.hashCode());
		result = prime * result + ((startLocation == null) ? 0 : startLocation.hashCode());
		result = prime * result + ((startTime == null) ? 0 : startTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RideRequest other = (RideRequest) obj;
		if (endLocation == null) {
			if (other.endLocation != null)
				return false;
		} else if (!endLocation.equals(other.endLocation))
			return false;
		if (passenger == null) {
			if (other.passenger != null)
				return false;
		} else if (!passenger.equals(other.passenger))
			return false;
		if (startLocation == null) {
			if (other.startLocation != null)
				return false;
		} else if (!startLocation.equals(other.startLocation))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RideRequest [startTime=" + startTime + ", startLocation=" + startLocation + ", endLocation="
				+ endLocation + ", passenger=" + passenger + "]";
	}

	public int getNumofPassengers() {
		return NumofPassengers;
	}

	public void setNumofPassengers(int numofPassengers) {
		NumofPassengers = numofPassengers;
	}
	
	
	
}
