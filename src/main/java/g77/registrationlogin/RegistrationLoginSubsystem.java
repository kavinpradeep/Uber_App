package g77.registrationlogin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import g77.common.entities.CabDetails;
import g77.common.entities.Driver;
import g77.common.entities.Passenger;
import g77.common.entities.PaymentPreference;
import g77.common.entities.PaymentType;
import g77.common.entities.User;

//Singleton
public class RegistrationLoginSubsystem {

		private List<Passenger> passenger = new ArrayList<Passenger>();
		private List<Driver> driver = new ArrayList<Driver>();
		private List<Passenger> loggedinPassenger = new ArrayList<Passenger>();
		private List<Driver> loggedinDriver = new ArrayList<Driver>();		
		private static RegistrationLoginSubsystem registrationLoginSubsystem = null;
		
		public RegistrationLoginSubsystem(){
			
			//Populate with some default Passenger and Drivers for easy access
			PopulateDefaultUsers();			
		}

		
		public static RegistrationLoginSubsystem getRegistrationLoginSubsystem(){
			if(registrationLoginSubsystem==null){
				registrationLoginSubsystem = new RegistrationLoginSubsystem();
			}
			
			return registrationLoginSubsystem;
				
		}
		
		public boolean CreateDriverProfile(User user){
			
			DriverRegistration driverRegistration = new DriverRegistration();
			
			driverRegistration.CreateProfile(user);
			return true;
		}
	
		
		public boolean CreatePassengerProfile(User user){
			PassengerRegistration passengerRegistration = new PassengerRegistration();
			
			passengerRegistration.CreateProfile(user);
			return true;
		}
		
				
		public List<Passenger> getPassenger() {
			return passenger;
		}
		
		public void setPassenger(List<Passenger> passenger) {
			this.passenger = passenger;
		}
		
		public List<Driver> getDriver() {
			return driver;
		}
		
		public void setDriver(List<Driver> driver) {
			this.driver = driver;
		}
	
		public User CreateUserinDB(User user){
			
			User dbUser = null;
			if(user instanceof Passenger ){
				dbUser = new Passenger();
			}else if (user instanceof Driver){
				dbUser = new Driver();
			}
			return dbUser;
		}
		
		public boolean StoreUserinDB(User dbUser){
			
			if(dbUser instanceof Passenger ){
				getPassenger().add((Passenger)dbUser);
			}else if (dbUser instanceof Driver){
				getDriver().add((Driver)dbUser);
			}
			return true;
			
		}
		
		private void PopulateDefaultUsers() {
			//Add some default users and drivers
			Passenger passenger1 = new Passenger();
			passenger1.setUserId(UUID.randomUUID());
			passenger1.setName("Passenger1");
			passenger1.setPassword("cloudcab123");
			PaymentPreference paymentPreference1 = new PaymentPreference();
			paymentPreference1.setPaymentType(PaymentType.PAYBYCARD);
			paymentPreference1.setName("Passenger1");
			paymentPreference1.setCardNumber("4313-4313-4313-4313");
			paymentPreference1.setCVCNumber("123");
			paymentPreference1.setCVCNumber("12/2020");
			passenger1.setPaymentPreference(paymentPreference1);
			getPassenger().add(passenger1);
			
			Passenger passenger2 = new Passenger();
			passenger2.setUserId(UUID.randomUUID());
			passenger2.setName("Passenger2");
			passenger2.setPassword("cloudcab123");
			PaymentPreference paymentPreference2 = new PaymentPreference();			
			paymentPreference2.setPaymentType(PaymentType.PAYBYPAYPAL);
			paymentPreference2.setPaypalLoginName("Passenger2");
			paymentPreference2.setPaypalpassword("******");
			passenger2.setPaymentPreference(paymentPreference2);
			getPassenger().add(passenger2);		
			
			Passenger passenger3 = new Passenger();
			passenger3.setUserId(UUID.randomUUID());
			passenger3.setName("Passenger3");
			passenger3.setPassword("cloudcab123");
			PaymentPreference paymentPreference3 = new PaymentPreference();
			paymentPreference3.setPaymentType(PaymentType.PAYBYCARD);
			paymentPreference3.setName("Passenger3");
			paymentPreference3.setCardNumber("4313-4313-4313-4313");
			paymentPreference3.setCVCNumber("123");
			paymentPreference3.setCVCNumber("12/2020");
			passenger3.setPaymentPreference(paymentPreference3);
			getPassenger().add(passenger3);
			
			Driver driver1 = new Driver();
			driver1.setUserId(UUID.randomUUID());
			driver1.setName("Driver1");
			driver1.setPassword("cloudcab123");
			PaymentPreference driverpayment1 = new PaymentPreference();
			driverpayment1.setPaymentType(PaymentType.PAYBYCARD);
			driverpayment1.setName("Driver1");
			driverpayment1.setCardNumber("4313-4313-4313-4313");
			driverpayment1.setCVCNumber("123");
			driverpayment1.setCVCNumber("12/2020");
			passenger3.setPaymentPreference(driverpayment1);	
			CabDetails cabDetails1 = new CabDetails();
			cabDetails1.setCabName("Cab1");
			cabDetails1.setCabType("Car");
			cabDetails1.setNumOfPassengers(3);
			cabDetails1.setVin("98765432109876543");
			cabDetails1.setLicensePlate("CAB007");
			driver1.setCabDetails(cabDetails1);
			getDriver().add(driver1);

			Driver driver2 = new Driver();
			driver2.setUserId(UUID.randomUUID());
			driver2.setName("Driver2");
			driver2.setPassword("cloudcab123");
			PaymentPreference driverpayment2 = new PaymentPreference();
			driverpayment2.setPaymentType(PaymentType.PAYBYCARD);
			driverpayment2.setName("Driver2");
			driverpayment2.setCardNumber("4313-4313-4313-4313");
			driverpayment2.setCVCNumber("123");
			driverpayment2.setCVCNumber("12/2020");
			driver2.setPaymentPreference(driverpayment2);
			CabDetails cabDetails2 = new CabDetails();
			cabDetails2.setCabName("Cab2");
			cabDetails2.setCabType("VAN");
			cabDetails2.setNumOfPassengers(6);
			cabDetails2.setVin("12345678901234567");
			cabDetails2.setLicensePlate("CAB001");
			driver2.setCabDetails(cabDetails2);
			getDriver().add(driver2);
		}


		public List<Passenger> getLoggedinPassenger() {
			return loggedinPassenger;
		}


		public void setLoggedinPassenger(List<Passenger> loggedinPassenger) {
			this.loggedinPassenger = loggedinPassenger;
		}


		public List<Driver> getLoggedinDriver() {
			return loggedinDriver;
		}


		public void setLoggedinDriver(List<Driver> loggedinDriver) {
			this.loggedinDriver = loggedinDriver;
		}



		
}
