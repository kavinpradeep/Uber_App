package g77.client;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import g77.common.entities.CabDetails;
import g77.common.entities.Driver;
import g77.common.entities.Location;
import g77.common.entities.UserType;
import g77.common.entities.Passenger;
import g77.common.entities.RideRequest;
import g77.service.ServiceRequest;

public class PassengerClient  {

	private boolean loggedin;
	private UserType typeofuser;
	private UUID usertoken;
	private UUID drivertoken = UUID.randomUUID();
	private UUID currentRideId = null;
	private boolean refresh = true;
	Random randomGenerator = new Random();
	
	
	PassengerClient(){
		loggedin = false;
		typeofuser = UserType.PASSENGER;
	}
	
	
	public void runPassengerClient(){
		
		Scanner in= new Scanner(System.in);
		//in.reset();
		while(loggedin==false){

			System.out.println("***************************************************");
			System.out.println("*****CLOUD CAB - Screen 02 - Login or SignUp *******");
			System.out.println("***************************************************");			
			if(typeofuser == UserType.PASSENGER){
				System.out.println("1. Passenger Login");
				System.out.println("2. Passenger SignUp");
				System.out.println("0. Exit");
			}else{
				System.out.println("3. Driver Login");
				System.out.println("4. Driver SignUp");
				System.out.println("0. Exit");
			}
			
			
			int x=in.nextInt(); 
			in.nextLine();
				switch(x){
				case 1:{
					System.out.println("Enter UserName: Bobby");
					System.out.println("Enter Password: *****");
					//TODO call the login and get the user token
					//usertoken
					usertoken = UUID.randomUUID();
					System.out.println("Login Successful !");
					loggedin=true;
					break;
				}
				case 2:{
					System.out.println("Passenger Registration");
					Passenger passenger=new Passenger();
					System.out.println("Enter Name");
			    	String name=in.next(); 
			    	in.nextLine();
			    	passenger.setName(name);
			    	System.out.println("Enter Address");
					String address=in.next(); 
					in.nextLine();
					passenger.setAddress(address);
					System.out.println("Enter Email");
					String email=in.next(); 
					in.nextLine();
					passenger.setEmail(email);
					System.out.println("Enter Contact Number");
					String contactNumber=in.next();
					passenger.setContactnumber(contactNumber);
					ServiceRequest.getServiceRequest().CreatePassengerProfile(passenger);
					System.out.println("Registration Successfull");	
					
				}
				case 4: {
					Driver driver=new Driver();
					System.out.println("Enter Name");
			    	String name=in.next(); 
			    	in.nextLine();
			    	driver.setName(name);
			    	System.out.println("Enter Address");
					String address=in.next(); 
					in.nextLine();
					driver.setAddress(address);
					System.out.println("Enter Email");
					String email=in.next(); 
					in.nextLine();
					driver.setEmail(email);
					System.out.println("Enter Contact Number");
					String contactNumber=in.next(); 
					driver.setContactnumber(contactNumber);
					CabDetails cab=new CabDetails();
					System.out.println("Enter Cab Details");
					System.out.println("Enter Cab Name");
			    	String cabName=in.next(); 
			    	in.nextLine();
			    	cab.setCabName(cabName);
			    	System.out.println("Enter Cab type");
			    	String cabType=in.next(); 
			    	in.nextLine();
			    	cab.setCabType(cabType);
			    	System.out.println("Enter Number of Passengers");
			    	int numOfPassengers=in.nextInt(); 
			    	in.nextLine();
			    	cab.setNumOfPassengers(numOfPassengers);
			    	System.out.println("Enter license Plate");
			    	String licensePlate=in.next(); 
			    	in.nextLine();
			    	cab.setLicensePlate(licensePlate);
			    	System.out.println("Enter Vin");
			    	String Vin=in.next(); 
			    	in.nextLine();
			    	cab.setVin(Vin);
			        driver.setCabDetails(cab);
					ServiceRequest.getServiceRequest().CreateDriverProfile(driver);
					System.out.println("Registration Successfull");
	
				}
				case 0: {
					System.out.println("Thanks!!! See you next time");
					System.exit(0);
				}
				default: System.out.println("Unknown Option - choose the correct option");
				}
		}
		
		ServiceRequest.getServiceRequest().driverLogin("Driver1","******");
		
		//in.reset();
		while(loggedin==true){

			if(typeofuser == UserType.PASSENGER){
				
				if(refresh){
				
					System.out.println("***********************************************************");
					System.out.println("*****CLOUD CAB - Screen 03 - Passenger - Dashboard  *******");
					System.out.println("***********************************************************");		
					System.out.println("1. Request for Ride");
					System.out.println("2. Add Tip");
					System.out.println("4. Rate Driver");				
					System.out.println("5. Cancel Ride");
					System.out.println("6. Ride History");
					System.out.println("7. Check Current Ride Status");
					System.out.println("0. Logout and Exit");
					
					
					System.out.println("***********************************************************");
					System.out.println("*****CLOUD CAB - Screen 04 - Driver - Dashboard  *******");
					System.out.println("***********************************************************");		
					System.out.println("10. Accept Notified Ride");
					System.out.println("20. Start Ride");
					System.out.println("30. Ride Complete and Payment");				
					System.out.println("40. Ride History");
					System.out.println("50. Check Current Ride Status");
					System.out.println("9");
					System.out.println("0. Logout and Exit");
					refresh=false;
				}
				
				//Scanner in= new Scanner(System.in);
				int x=in.nextInt(); 	
				in.nextLine();
					switch(x){
					case 1: {
						
						RideRequest rideRequest = new RideRequest();
						System.out.println("Enter Ride Request Details");					
						System.out.println("Enter Ride Start Location(City-SanJose\\Sunnyvale\\Cupertino\\SFO)");
						//Location startLocation = 
						String startLocation = in.nextLine();
						Location startLoc = new Location(startLocation);
						rideRequest.setStartLocation(startLoc);
						//System.out.println("Ride Start Location:"+ startLocation);

						System.out.println("Enter Ride End Location(City-SanJose\\Sunnyvale\\Cupertino\\SFO)");
						//Location startLocation = 
						String endLocation = in.nextLine();
						Location endLoc = new Location(endLocation);
						rideRequest.setEndLocation(endLoc);					
						//System.out.println("Ride End Location:"+ endLocation);
						
						System.out.println("Enter Start time(now)");
						//Location startLocation = 
						String startTimedstr = in.nextLine();
						Date startTime = new Date();
						if(!startTimedstr.equals("now")){
							//startTime.setMinutes();
						}
						rideRequest.setStartTime(startTime);
						//System.out.println("StartTime:" + startTimedstr);
						
						Passenger passenger = new Passenger();
						String passengerName = in.nextLine();
						System.out.println("Enter Passenger Name" + passengerName);
						passenger.setName(passengerName);
						rideRequest.setPassenger(passenger);
						
						currentRideId = ServiceRequest.getServiceRequest().requestRide(usertoken, rideRequest);
						break;
					}
					case 2: {
						//Add tip to current in ride
						if(currentRideId == null){
							System.out.println("No active ride to add tip");
						}
						System.out.println("Enter tip for ride "+ currentRideId);
						int tip = in.nextInt(); 	
						in.nextLine();
						ServiceRequest.getServiceRequest().addTip(usertoken, currentRideId, tip);
					}
					
					case 9: {
						
						refresh = true;
						System.out.println("About to Refreshing screen !!! ");
						break;
					}
					
					
					case 0: {
						System.out.println("Thanks!!! See you next time");
						System.exit(0);
					}
					
					
					case 10: {
						System.out.println("Processing Driver Acceptance ... ");
						ServiceRequest.getServiceRequest().acceptRide(drivertoken);
						System.out.println("Processing Driver Acceptance ... Completed");
						break;
					}
					case 20: {
						System.out.println("Ride Start Processing ... ");
						ServiceRequest.getServiceRequest().RideStart(drivertoken);
						System.out.println("Ride Start Processing ... Completed");
						break;
					}						
					
					case 30: {
						System.out.println("Ride Complete Processing ... ");
						ServiceRequest.getServiceRequest().RideCompleted(drivertoken);
						System.out.println("Ride Complete Processing ... Completed");
						break;
					}					
					
					
					default: System.out.println("Unknown Option - choose the correct option");
					}
			}
		}			
	}
	
}
