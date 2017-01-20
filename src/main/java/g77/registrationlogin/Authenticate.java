package g77.registrationlogin;

import java.util.UUID;

import g77.common.entities.Driver;
import g77.common.entities.Passenger;
import g77.common.entities.User;
import g77.ride.RideSubsystem;

public class Authenticate {

	private static Authenticate authenticate = null;
	
	private Authenticate(){
		
	}
	
	public static Authenticate getAuthenticateInstance(){
		if(authenticate==null){
			authenticate = new Authenticate();
		}
		return authenticate;
	}
	
	public Driver loginDriver(String username, String password){
	
		Driver driver = null;
		for(Driver d : RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getDriver()){
			if(d.getName().equals(username) && d.getPassword().equals(password)){
				driver = d;
			}
		}
		
		RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getLoggedinDriver().add(driver);
		if(driver!=null){
			RideSubsystem.getRideSubSystem().addDrivertoTrack(driver);
			System.out.println("Add Driver to Tracker ;"+ driver);
		}	
		
		return driver;
		
	}
	
	public Passenger loginPassenger(String username, String password){
		
		Passenger passenger = null;
		for(Passenger p : RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getPassenger()){
			if(p.getName().equals(username) && p.getPassword().equals(password)){
				passenger = p;
			}
		}
		
		RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getLoggedinPassenger().add(passenger);
		
		return passenger;
		
	}	
	
	public Passenger getLoggedinPassenger(UUID usertoken){
		
		Passenger passenger = null;
		for(Passenger p : RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getLoggedinPassenger()){
			if(p.getUserId().equals(usertoken)){
				passenger = p;
			}
		}
		return passenger;
	}
	
	public Driver getLoggedinDriver(UUID usertoken){
	
		Driver driver = null;
		for(Driver d : RegistrationLoginSubsystem.getRegistrationLoginSubsystem().getLoggedinDriver()){
			if(d.getUserId().equals(usertoken)){
				driver = d;
			}
		}
		return driver;
	}
	
	
}
