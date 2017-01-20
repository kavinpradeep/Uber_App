package g77.common.entities;

public class Location {
	
		double latitude;
		double longtitude;
		String streeAddress;
		String city;
		String state;
		String zipcode;

		public Location(String city){
			this.setCity(city);
			this.updateLocation(0,0);
		}
		
		public Location(double latitude, double longtitude){
			this.latitude = latitude;
			this.longtitude = longtitude;
			
		}
		
		public String getStreeAddress() {
			return streeAddress;
		}
		
		public void setStreeAddress(String streeAddress) {
			this.streeAddress = streeAddress;
		}
		
		public String getCity() {
			return this.city;
		}
		
		public void setCity(String city) {
			this.city = city;
		}
		
		public String getState() {
			return state;
		}
		
		public void setState(String state) {
			this.state = state;
		}
		
		public String getZipcode() {
			return zipcode;
		}
		
		public void setZipcode(String zipcode) {
			this.zipcode = zipcode;
		}	
		
		public boolean updateLocation(String city){
			this.city = city;
			return true;
		}
		
		public boolean updateLocation(double latitude,double longtitude){
			this.latitude = latitude;
			this.longtitude = longtitude;
			return true;
		}
		
		public boolean isNearbyAndOnline(Location loc){
			
				if(this.latitude == loc.latitude && this.longtitude == this.longtitude){
					return true;
				}else{
					return false;
				}
		}

		@Override
		public String toString() {
			return "Location [latitude=" + latitude + ", longtitude=" + longtitude + ", city=" + city + "]";
		}
		
		
}
