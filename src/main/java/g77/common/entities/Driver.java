package g77.common.entities;

public class Driver extends User{

	private CabDetails cabDetails;

	public CabDetails getCabDetails() {
		return cabDetails;
	}

	public void setCabDetails(CabDetails cabDetails) {
		this.cabDetails = cabDetails;
	}

	@Override
	public String toString() {
		return "Driver [name=" + name + ", userId=" + userId + ", cabDetails=" + cabDetails + ".]";
	}	
	
}
