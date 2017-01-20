package g77.common.entities;

public class CabDetails {

	private String cabName;
	private String cabType;
	private int numOfPassengers;
	private String licensePlate;
	private String Vin;
	
	public String getCabName() {
		return cabName;
	}
	public void setCabName(String cabName) {
		this.cabName = cabName;
	}
	public String getCabType() {
		return cabType;
	}
	public void setCabType(String cabType) {
		this.cabType = cabType;
	}
	public int getNumOfPassengers() {
		return numOfPassengers;
	}
	public void setNumOfPassengers(int numOfPassengers) {
		this.numOfPassengers = numOfPassengers;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getVin() {
		return Vin;
	}
	public void setVin(String vin) {
		Vin = vin;
	}
	@Override
	public String toString() {
		return "CabDetails [cabName=" + cabName + ", cabType=" + cabType + ", numOfPassengers=" + numOfPassengers
				+ ", licensePlate=" + licensePlate + ", Vin=" + Vin + "]";
	}
	
	
}
