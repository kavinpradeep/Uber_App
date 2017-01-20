package g77.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import g77.common.entities.Driver;
import g77.common.entities.Passenger;
import g77.common.entities.PaymentPreference;
import g77.common.entities.PaymentType;
import g77.common.entities.RideRequest;
import g77.registrationlogin.Authenticate;
import g77.registrationlogin.RegistrationLoginSubsystem;
import g77.report.ReportGenerator;
import g77.ride.Ride;
import g77.ride.RideSubsystem;

//Facade
public class ServiceRequest {

	private static ServiceRequest serviceRequest= null;
	
	private ServiceRequest(){
		
	}
	
	public static ServiceRequest getServiceRequest(){
		if(serviceRequest==null){
			serviceRequest = new ServiceRequest();
		}
		return serviceRequest;
	}
	
	private Passenger getPassenger(UUID token){
		for(Passenger p :RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getLoggedinPassenger()){
			if(p.getUserId().equals(token)){
				return p;
			}
		}
		return null;
	}
	
	private Driver getDriver(UUID token){
		for(Driver d :RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getLoggedinDriver()){
			if(d.getUserId().equals(token)){
				return d;
			}
		}
		return null;
	}
	
	public boolean CreatePassengerProfile(Passenger passenger){
		
		//TODO - add  validation - check if already exist - return false
		RegistrationLoginSubsystem.getRegistrationLoginSubsystem().CreatePassengerProfile(passenger);
		return true;
	}
	
	public boolean CreateDriverProfile(Driver driver){
		
		//TODO - add  validation. - check if already exist - return false
		RegistrationLoginSubsystem.getRegistrationLoginSubsystem().CreateDriverProfile(driver);
		return true;
	}
	
	public List<Passenger> GetAllPassengers(){
		return RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getPassenger();
	}
	
	public List<Driver> GetAllDrivers(){
		return RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getDriver();
	}
	
	
	public UUID passengerlogin(String username, String password){
		
		Passenger passenger = Authenticate.getAuthenticateInstance().loginPassenger(username, password);
		
		if(passenger!=null){
			System.out.println("Login Successfull for Passenger Name -" + passenger.getName());
			return passenger.getUserId();
			
		}else{
			System.out.println("Login Failed " + passenger.getName());
			return null;
		}	
		
	}
	
	public UUID driverLogin(String username, String password){
		
		Driver driver = Authenticate.getAuthenticateInstance().loginDriver(username, password);
		
		if(driver!=null){
			System.out.println("Login Successfull for Driver Name -" + driver.getName());
			return driver.getUserId();
			
		}else{
			System.out.println("Login Failed " + driver.getName());
			return null;
		}	
	}
	
	public UUID requestRide(UUID usertoken, RideRequest rideRequest ){
		
		Passenger passenger = getPassenger(usertoken); 
		if(passenger==null){
			System.out.println("Unable to find Passenger Id: " + usertoken);
			return null;
		}
		
		rideRequest.setPassenger(passenger);
		
		//send the request to ride subsystem
		UUID rideId = RideSubsystem.getRideSubSystem().requestRide(rideRequest);
				
		return rideId;
	}
	
	public boolean addTip(UUID usertoken, UUID rideId, int tip){
		
		Passenger passenger = getPassenger(usertoken); 
		if(passenger==null){
			System.out.println("Unable to find Passenger Id: " + usertoken);
			return false;
		}
		
		//send the request to ride subsystem
		RideSubsystem.getRideSubSystem().addTip(rideId,passenger, tip);
				
		return true;
	}
	
	public boolean RideCancelledbyPassenger(UUID usertoken, UUID rideId){
		
		Passenger passenger = getPassenger(usertoken); 
		if(passenger==null){
			System.out.println("Unable to find Passenger Id: " + usertoken);
			return false;
		}
		
		//send the request to ride subsystem
		return RideSubsystem.getRideSubSystem().RideCancelledbyPassenger(rideId,passenger);
		
	}	
	
	
	public boolean acceptRide(UUID usertoken){	

		
		Driver driver = getDriver(usertoken); 
		if(driver==null){
			System.out.println("Unable to find Driver ID: " + usertoken);
			return false;
		}
		
		RideSubsystem.getRideSubSystem().acceptRide(driver);
		
		return true;
		
	}
	
	public boolean RideStart(UUID usertoken){

		Driver driver = getDriver(usertoken); 
		if(driver==null){
			System.out.println("Unable to find Driver ID: " + usertoken);
			return false;
		}
		
		RideSubsystem.getRideSubSystem().RideStart(driver);		
		return true;
	}
	
	public boolean RideCompleted(UUID usertoken){
		
		Driver driver = getDriver(usertoken); 
		if(driver==null){
			System.out.println("Unable to find Driver ID: " + usertoken);
			return false;
		}
		
		RideSubsystem.getRideSubSystem().RideCompleted(driver);
		return true;
	}

	
	public boolean RideCancelledbyDriver(UUID usertoken){	
		
		Driver driver = getDriver(usertoken); 
		if(driver==null){
			System.out.println("Unable to find Driver ID: " + usertoken);
			return false;
		}
		
		RideSubsystem.getRideSubSystem().RideCancelledbyDriver(driver);
		return true;
	}
		
	
	public List<Passenger> GetAllPAssengers(UUID usertoken){
		
		return RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getPassenger();
	}
	
	public List<Driver> GetAllDrivers(UUID usertoken){
		
		return RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getDriver();
	}
	
	public ConcurrentLinkedQueue<Ride> getAllRides(){
		return ReportGenerator.getReportGeneratorInstance().getAllRides();
	}
	
	public boolean setDriverLocation(String City){	

		//Driver  driver = RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getDriver().get(0);
		
		//RideSubsystem.getRideSubSystem().acceptRide(driver);
		
		return true;
		
	}
	
}
