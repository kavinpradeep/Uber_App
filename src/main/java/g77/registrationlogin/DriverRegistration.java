package g77.registrationlogin;

import g77.common.entities.Driver;
import g77.common.entities.User;

public class DriverRegistration extends Registration {

	
	@Override
	public void AdditonalProfileCreation(User dbUser, User user) {
		
		AddCabDetails((Driver)dbUser, (Driver)user);
		
	}

	public boolean isAnyAdditonalProfileCreation(){
		return true;
	}
	
	private void AddCabDetails(Driver dbDriver, Driver driver){
		dbDriver.setCabDetails(driver.getCabDetails());
	}

}
