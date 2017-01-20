package g77.client;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import g77.common.entities.CabDetails;
import g77.common.entities.Driver;
import g77.common.entities.Location;
import g77.common.entities.Passenger;
import g77.common.entities.PaymentPreference;
import g77.common.entities.PaymentType;
import g77.common.entities.RideRequest;
import g77.ride.Ride;
import g77.service.ServiceRequest;

//Use case Description
//UC01	Register 
//UC02	Login
//UC03	Request for Ride
//UC04	Calculate Route
//UC05	Accept the Ride
//UC06	Cancel the Ride
//UC07	Update Ride Status
//UC08	Process Payment
//UC09	Pay by PayPal
//UC10	Pay by Card
//UC11	Pay for Ride
//UC12	Pay for Registeration
//UC13	Generate Report
//UC14	Update Fare Criteria
//UC15	Track Ride
//UC16	Update Notification Criteria


public class ClientApplication {
	
	public void runPassengerClient(){

		
		
		boolean refresh = true;
		boolean loggedin = true;
//		HashMap<String,UUID> listOfPassengerIDS = new HashMap<String, UUID>();
//		HashMap<String,UUID> listOfDriverIDS = new HashMap<String, UUID>();
		
		String currPassengerName = "";
		String currDriverName = "";
		UUID usertoken = null;
		UUID drivertoken = null;
		UUID currentRideId =null;
		currPassengerName="Passenger1";
		currDriverName="Driver1";
		drivertoken = ServiceRequest.getServiceRequest().driverLogin(currDriverName,"cloudcab123");
		usertoken = ServiceRequest.getServiceRequest().passengerlogin(currPassengerName,"cloudcab123");
		
		while(loggedin==true){
				Scanner in= new Scanner(System.in);
				if(refresh){
								
					System.out.println("***************************************************");
					System.out.println("*****CLOUD CAB - Screen 01/02 - Login or SignUp *******");
					System.out.println("***************************************************");							
					System.out.println("	11. Register-Passenger");
					System.out.println("	12. Register-Driver");
					System.out.println("	21. Login-Passenger");
					System.out.println("	22. Login-Driver");
					
					System.out.println("***********************************************************");
					System.out.println("*****CLOUD CAB - Screen 03 - Passenger - Dashboard  *******");
					System.out.println("***********************************************************");		
					System.out.println("	31. Request for Ride");
					System.out.println("	32. Process Payment - Add Tip");
					System.out.println("	33. Cancel Ride");								
					
					System.out.println("***********************************************************");
					System.out.println("*****CLOUD CAB - Screen 04 - Driver - Dashboard  *******");
					System.out.println("***********************************************************");		
					System.out.println("	41. Accept Notified Ride");
					System.out.println("	42. Start Ride");
					System.out.println("	43. Ride Complete and Payment");				
					System.out.println("	46. Notify Problem and Cancel Driver Acceptance");
					
					System.out.println("***********************************************************");
					System.out.println("*****CLOUD CAB - Screen 05 - Driver - Dashboard  *******");
					System.out.println("***********************************************************");						
					System.out.println("    51. Generate Report - All Passengers");
					System.out.println("    52. Generate Report - All Drivers");
					System.out.println("    53. Generate Report - All Rides");
					System.out.println("***********************************************************");
					System.out.println("	0. Exit");
					System.out.println("	9. Refresh");	
					System.out.println("	8. Change current driver location");	
					System.out.println("***********************************************************");
					
					System.out.println("The current Passenger logged in :" + currPassengerName );
					System.out.println("The current Driver logged in    :" + currDriverName);
					
					refresh=false;
				}

				System.out.println("");
				System.out.println("");
				System.out.println("");
				
				try{
				
				//Scanner in= new Scanner(System.in);
				int x=in.nextInt(); 	
				in.nextLine();
					switch(x){
					
					case 11:{//UC01	Register 
						System.out.println("***********************************************************");
						System.out.println("                Passenger Registration");
						System.out.println("***********************************************************");
						Passenger passenger=new Passenger();
						System.out.println("Enter Name");
				    	String name=in.next(); 
				    	in.nextLine();
				    	passenger.setName(name);
						System.out.println("Enter Password");
				    	String password =in.next(); 
				    	in.nextLine();
				    	passenger.setPassword(password);				    	
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
						System.out.println("Enter Payment Preference: 1-PaybyCard 2-PaybyPaypal :");
						PaymentPreference paymentPreference = new PaymentPreference();
						paymentPreference.setName(passenger.getName());
						int paymentType  = in.nextInt(); 	
						in.nextLine();
						if(paymentType==1){
							paymentPreference.setPaymentType(PaymentType.PAYBYCARD);
							System.out.println("Enter Payment Card Number:");
							String cardNumber=in.next(); 
							in.nextLine();
							paymentPreference.setCardNumber(cardNumber);
							System.out.println("Enter Payment CVC:");
							String cvc=in.next(); 
							in.nextLine();
							paymentPreference.setCVCNumber(cvc);
							System.out.println("Enter Payment Validity:");
							String validity=in.next(); 
							in.nextLine();
							paymentPreference.setValidTill(validity);
						}else{
							paymentPreference.setPaymentType(PaymentType.PAYBYPAYPAL);
							System.out.println("Enter Paypal Account Name:");
							String paypalaccname =in.next(); 
							in.nextLine();
							paymentPreference.setPaypalLoginName(paypalaccname);
							System.out.println("Enter Paypal Password:");
							String paypalpassword=in.next(); 
							in.nextLine();
							paymentPreference.setPaypalpassword(paypalpassword);
						}
						passenger.setPaymentPreference(paymentPreference);
						ServiceRequest.getServiceRequest().CreatePassengerProfile(passenger);
						System.out.println("Registration Successfull");	
						break;
						
					}
					case 12: {//UC01	Register 
						System.out.println("***********************************************************");
						System.out.println("                DRIVER REGISTRATION");
						System.out.println("***********************************************************");
						Driver driver=new Driver();
						System.out.println("Enter Name");
				    	String name=in.next(); 
				    	in.nextLine();
				    	driver.setName(name);
						System.out.println("Enter Password");
				    	String password =in.next(); 
				    	in.nextLine();
				    	driver.setPassword(password);					    	
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
						System.out.println("Enter Payment Preference: 1-PaybyCard 2-PaybyPaypal :");
						PaymentPreference paymentPreference = new PaymentPreference();
						paymentPreference.setName(driver.getName());
						int paymentType  = in.nextInt(); 	
						in.nextLine();
						if(paymentType==1){
							paymentPreference.setPaymentType(PaymentType.PAYBYCARD);
							System.out.println("Enter Payment Card Number:");
							String cardNumber=in.next(); 
							in.nextLine();
							paymentPreference.setCardNumber(cardNumber);
							System.out.println("Enter Payment CVC:");
							String cvc=in.next(); 
							in.nextLine();
							paymentPreference.setCVCNumber(cvc);
							System.out.println("Enter Payment Validity:");
							String validity=in.next(); 
							in.nextLine();
							paymentPreference.setValidTill(validity);
						}else{
							System.out.println("Enter Paypal Account Name:");
							String paypalaccname =in.next(); 
							in.nextLine();
							paymentPreference.setPaypalLoginName(paypalaccname);
							System.out.println("Enter Paypal Password:");
							String paypalpassword=in.next(); 
							in.nextLine();
							paymentPreference.setPaypalpassword(paypalpassword);
						}
						driver.setPaymentPreference(paymentPreference);
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
						break;
					}
					case 21:{//UC02	Login
						System.out.println("Enter Passenger Name:");
				    	String username=in.next(); 
				    	in.nextLine();
						System.out.println("Enter Passenger Password :");
				    	String password=in.next(); 
				    	in.nextLine();
				    	UUID token = ServiceRequest.getServiceRequest().passengerlogin(username, password);
				    	if(token!=null){
				    		usertoken=token;
				    		currPassengerName = username;
				    		System.out.println("Passenger Login Successful !");
				    	}
						loggedin=true;
						in.nextLine();
						refresh = true;
						break;
					}
					case 22:{//UC02	Login
						System.out.println("Enter Driver Name:");
				    	String username=in.next(); 
				    	in.nextLine();
						System.out.println("Enter Driver Password :");
				    	String password=in.next(); 
				    	in.nextLine();
				    	UUID token = ServiceRequest.getServiceRequest().driverLogin(username, password);
				    	if(token!=null){
				    		drivertoken = token;
					    	currDriverName = username;
							System.out.println("Driver Login Successful !");
				    	}
						loggedin=true;
						in.nextLine();
						refresh = true;
						break;
					}
					
					case 31: {//UC03	Request for Ride
						//UC04	Calculate Route
						RideRequest rideRequest = new RideRequest();
						System.out.println("Enter Ride Request Details");		
						System.out.println("Available Cities:Sunnyvale,Cupertino,San Jose,");
						System.out.println("Milpitas,Fremont,Alum Rock,Los Gatos,Robertsville,");
						System.out.println("Gilroy,Mountain View,Palo Alto,San Mateo,Hayward,Union City");						
						System.out.println("Enter Ride Start Location:");
						String startLocation = in.nextLine();
						Location startLoc = new Location(startLocation);
						rideRequest.setStartLocation(startLoc);
						//System.out.println("Ride Start Location:"+ startLocation);

						System.out.println("Available Cities:Sunnyvale,Cupertino,San Jose,");
						System.out.println("Milpitas,Fremont,Alum Rock,Los Gatos,Robertsville,");
						System.out.println("Gilroy,Mountain View,Palo Alto,San Mateo,Hayward,Union City");
						System.out.println("Enter Ride End Location:");
						String endLocation = in.nextLine();
						Location endLoc = new Location(endLocation);
						rideRequest.setEndLocation(endLoc);					
						//System.out.println("Ride End Location:"+ endLocation);
						
						System.out.println("Enter Start time(now)|yyyyy-mm-dd hh:mm:ss");
						//Location startLocation = 
						String startTimedstr = in.nextLine();
						Date startTime = new Date();
						if(!startTimedstr.equals("now")){
							SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
							startTime = dt.parse(startTimedstr);
						}
						rideRequest.setStartTime(startTime);
						System.out.println("StartTime:" + startTimedstr);
						
						Passenger passenger = new Passenger();
						passenger.setName(currPassengerName);
						rideRequest.setPassenger(passenger);
						
						UUID rideId = ServiceRequest.getServiceRequest().requestRide(usertoken, rideRequest);
						if(rideId!=null){
							currentRideId = rideId;
							System.out.println("Ride Request Submitted -RideID:" + currentRideId);
						}else{
							System.out.println("Ride Request Failed !!!");
						}
						
						break;
					}
					case 32: {
						//UC08	Process Payment
						//Add tip to current in ride
						if(currentRideId == null){
							System.out.println("No active ride to add tip");
						}
						System.out.println("Enter tip for ride "+ currentRideId);
						int tip = in.nextInt(); 	
						in.nextLine();
						ServiceRequest.getServiceRequest().addTip(usertoken, currentRideId, tip);
						break;
					}

					case 33: {
						//Cancel Ride
						//UC06	Cancel the Ride
						if(currentRideId == null){
							System.out.println("No ride selected, cannot cancel the ride.");
						}
						System.out.println("About to cancel the current Ride " + currentRideId + "!!!");
						ServiceRequest.getServiceRequest().RideCancelledbyPassenger(usertoken, currentRideId);
						break;
					}
					
					case 41: {
						//UC05	Accept the Ride
						if(currentRideId == null){
							System.out.println("No ride selected, cannot accept the ride.");
						}
						System.out.println("Processing Driver Acceptance ... ");
						ServiceRequest.getServiceRequest().acceptRide(drivertoken);
						System.out.println("Processing Driver Acceptance ... Completed");
						break;
					}
					case 42: {
						//UC07	Update Ride Status
						if(currentRideId == null){
							System.out.println("No ride selected, cannot Start the ride.");
						}
						System.out.println("Ride Start Processing ... ");
						ServiceRequest.getServiceRequest().RideStart(drivertoken);
						System.out.println("Ride Start Processing ... Completed");
						break;
					}						
					
					case 43: {
						//UC07	Update Ride Status
						if(currentRideId == null){
							System.out.println("No ride selected, cannot complete the ride.");
						}
						System.out.println("Ride Complete Processing ... ");
						ServiceRequest.getServiceRequest().RideCompleted(drivertoken);
						System.out.println("Ride Complete Processing ... Completed");
						break;
					}					
					case 46: {
						//UC06	Cancel the Ride
						if(currentRideId == null){
							System.out.println("No ride selected, cannot cancel the ride.");
						}
						System.out.println("Ride Cancel Processing ... ");
						ServiceRequest.getServiceRequest().RideCancelledbyDriver(drivertoken);
						System.out.println("Ride Cancel Processing ... Completed");
						break;
					}
					
					case 51: {
						//UC13	Generate Report
						System.out.println("************************************************************");
						System.out.println("			REPORT - All Passengers");
						System.out.println("************************************************************");
						List<Passenger> passengers = ServiceRequest.getServiceRequest().GetAllPassengers();
						for(Passenger p : passengers){
							System.out.println(p.toString());
						}
						System.out.println("************************************************************");
						break;
					}							
					case 52: {
						//UC13	Generate Report
						System.out.println("************************************************************");
						System.out.println("			REPORT - All Drivers");
						System.out.println("************************************************************");
						List<Driver> drivers = ServiceRequest.getServiceRequest().GetAllDrivers();
						for(Driver d : drivers){
							System.out.println(d.toString());
						}
						System.out.println("************************************************************");
						break;
					}		
					case 53: {
						//UC13	Generate Report
						System.out.println("************************************************************");
						System.out.println("			REPORT - All Rides");
						System.out.println("************************************************************");
						ConcurrentLinkedQueue<Ride> rides = ServiceRequest.getServiceRequest().getAllRides();
						for(Ride r : rides){
							System.out.println(r.toString());
						}
						System.out.println("************************************************************");
						break;
					}			
					
					case 8: {
						
						break;
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
					
					default: System.out.println("Unknown Option - choose the correct option");
					}
				}catch(Exception e){
					System.out.println("ERROR While Processing - restarting ...");
					in.reset();
					refresh=true;
				}
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				System.out.println("");
				
			}		
	}
}
