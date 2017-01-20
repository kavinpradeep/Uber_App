package g77.ride;

import java.net.Authenticator.RequestorType;
import java.util.Iterator;

import g77.common.entities.Driver;
import g77.notification.NotificationManager;

public class RideScheduler implements Runnable {

	private Thread t;
	private String ThreadName = "RideScheduler";
	boolean isRunning = true;
	
	public synchronized boolean isRunning() {
		return isRunning;
	}

	public synchronized void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void run() {
		
			processRequest();
		
	}
	
	public void start(){
		
	      System.out.println("Starting " + ThreadName );
	      if (t == null)
	      {
	         t = new Thread (this, ThreadName);
	         t.start ();
	      }
	}
	
	private void processRequest(){
		
		while(isRunning()){
			
			Iterator<Ride> iterator = RideSubsystem.getRideSubSystem().getRequestedRides().iterator();

			while(iterator.hasNext()) {
				
					Ride ride = iterator.next();				
					
					//find a driver and get his acceptance
					Driver d = RideSubsystem.getRideSubSystem().getNearbyDriver(ride.getRideRequest().getStartLocation());
					
					if(d ==null){
						System.out.println("No Driver Found" + d);
						if(ride.getRideState().getRideState().equals(RideState.rideStateRequested)){
							synchronized(RideSubsystem.getRideSubSystem().getRequestedRides()){
								ride.RideCancel();
							}
						}
						continue;
					}
					
					System.out.println("Found nearbyDriver - " + d);
					
					//notify driver and wait for 30 seconds for driver to confirm
					NotificationManager.getNotificationManager().notifyDriver(d, "Accept Ride ?  - " + ride);
					
					boolean driverFound = false;
					for(int i=0;i<5;i++){
						
						if(!ride.getRideState().getRideState().equals(RideState.rideStateRequested)){
							break;
						}
						
						if(RideSubsystem.getRideSubSystem().isDriverBusy(d)){
							//System.out.println("DRIVER ACCEPTED THE REQUEST !!!");
							NotificationManager.getNotificationManager().notifyPassenger(ride.getRideRequest().getPassenger(),"DRIVER ACCEPTED THE REQUEST !!!");
							synchronized(RideSubsystem.getRideSubSystem().getRequestedRides()){
								ride.setDriver(d);
								ride.ConfirmRide();
								driverFound = true;
							}
							break;
						}
						NotificationManager.getNotificationManager().notifyPassenger(ride.getRideRequest().getPassenger(),"WAITING FOR DRIVER TO ACCEPT !!! - waiting count" + i);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
