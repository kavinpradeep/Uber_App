package g77.client;

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import g77.common.entities.Location;
import g77.common.entities.UserType;
import g77.common.entities.Passenger;
import g77.common.entities.RideRequest;
import g77.service.ServiceRequest;

public class cloudcabclient {

	public static void main(String[] args) {
		
		System.out.println("*********************************************************");
		System.out.println("******************WELCOME TO CLOUD CAB*******************");
		System.out.println("*********************************************************");
		boolean exit=false;
		boolean loggedin = false;
		UserType typeofuser = null;
		UUID usertoken = UUID.randomUUID();
//		boolean loggedin = true;
//		LoginType typeofuser = LoginType.PASSENGER;		
		
		
		ClientApplication client = new ClientApplication();
		client.runPassengerClient();
		
//		Scanner in= new Scanner(System.in);
//		
//		in.reset();
//		
//		while(typeofuser==null){
//
//			System.out.println("***************************************************");
//			System.out.println("*****CLOUD CAB - Screen 01 - Choose User Type ********");
//			System.out.println("***************************************************");
//			System.out.println("1. Passenger");
//			System.out.println("2. Driver");
//			System.out.println("3. Admin");
//			System.out.println("0. Exit");
//			
//			int x=in.nextInt(); 
//			in.nextLine();
//			switch(x){
//			case 1: {
//				typeofuser = UserType.PASSENGER; 
//				PassengerClient pc = new PassengerClient();
//				pc.runPassengerClient();
//				break;
//			
//			}
//			case 2: {
//				typeofuser = UserType.DRIVER; 
//				//DriverClient dc= new DriverClient();
//				break;
//			}
//			case 3: typeofuser = UserType.ADMIN; break;
//			case 0: {
//				System.out.println("Thanks!!! See you next time");
//				System.exit(0);
//			}
//			default: System.out.println("Unknown Option - choose the correct option");
//			}
//		}
		

		
		
//		ServiceRequest.getServiceRequest().CreateProfile();
//		ServiceRequest.getServiceRequest().login();
	}

}


//int b;
//try {
//	b = System.in.read();
//	System.out.println("You typed: " + b);
//	  if(b==48){
//		  exit=true;
//	  }
//	  
//} catch (IOException e) {
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//}	


//Notes

// Can the ridePayment be changed as Decorator pattern.
//