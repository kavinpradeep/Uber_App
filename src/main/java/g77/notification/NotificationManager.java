package g77.notification;

import g77.common.entities.Driver;
import g77.common.entities.Passenger;
import g77.ride.IRideStateObserver;
import g77.ride.Ride;
import g77.ride.RideState;

public class NotificationManager implements IRideStateObserver{
		
	private static NotificationManager notificationManager=null;
	
	private NotificationManager(){
		
	}
	
	public static NotificationManager getNotificationManager(){
		
		if(notificationManager==null){
			notificationManager = new NotificationManager();
		}
		return notificationManager;
	}
	
	public synchronized void RideStateChanged(Ride ride, RideState previousState, RideState CurrentState) {
		
		System.out.println("");
		System.out.println("Notification: RIDE STATE CHANGED - " + "Previous State:" +  previousState + ", CurrentState:" + CurrentState );
		System.out.println("");
		
	}
	
	public synchronized void notifyDriver(Driver driver, String message){
	
		System.out.println("");
		System.out.println("Notification(To Driver):    " + message);
		System.out.println("");
	}
	
	
	public synchronized void notifyPassenger(Passenger passenger, String message){
		
		System.out.println("");
		System.out.println("Notification(To Passenger):    " + message);
		System.out.println("");
	}
	
}
