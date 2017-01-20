package g77.ride;

import java.util.Iterator;

import g77.notification.NotificationManager;
import g77.payment.PaymentMode;
import g77.ride.fare.PrimeFareCalculator;
import g77.ride.fare.RegularFareCalculator;
import g77.ride.fare.RideFareCalculator;

public class RideTracker implements Runnable {

	private Thread t;
	private String ThreadName = "RideTracker";
	boolean isRunning = true;
	
	public synchronized boolean isRunning() {
		return isRunning;
	}
	
	public synchronized void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void run() {
		
			TrackRide();
		
	}
	
	public void start(){
		
	      System.out.println("Starting " + ThreadName );
	      if (t == null)
	      {
	         t = new Thread (this, ThreadName);
	         t.start ();
	      }
	}
	
	private void TrackRide(){
		
		
		while(isRunning()){ 
		
			//System.out.println("TrackRide running ....");
			Iterator<Ride> iterator = RideSubsystem.getRideSubSystem().getInProgressRides().iterator();
	
			while(iterator.hasNext()) {
				
				Ride ride = iterator.next();
				//System.out.println("TrackRide processing ride .... -" + ride);
				//Track the confirmed ride
				// if Ride not started, it will track and update the Arrival - ETA
				// if Ride is started, it will track and update the current location and update time to reach destination
				// once ride completed, it will send to do the payment process
				// send notification in each stage.
										
				if(ride.getRideState().getRideState().equals(RideState.rideStateConfirmed)){
					
					//Track the location of Driver and Update ETA for ride to start.
					//TODO calculate ETA.
					ride.setETA(10);
					NotificationManager.getNotificationManager().notifyPassenger(ride.getRideRequest().getPassenger(),
								"Driver will reach starting location in " + ride.getETA() + " mins");
					
				}else if(ride.getRideState().getRideState().equals(RideState.rideStateInProgress)){
					
					synchronized(RideSubsystem.getRideSubSystem().getInProgressRides()){
						RideFareCalculator rideFareCalculator = null;
						if(ride.getRideRequest().getStartTime().getHours() > 21 || ride.getRideRequest().getStartTime().getHours() < 6 ){
							rideFareCalculator= new PrimeFareCalculator(ride);
						}else{
							rideFareCalculator = new RegularFareCalculator(ride);
						}
						ride.setActualFare(rideFareCalculator.CalculateFare());
					}
					NotificationManager.getNotificationManager().notifyPassenger(ride.getRideRequest().getPassenger(),
							"RIDE IN PROGRESS: RideId:" + ride.getRideId() + ", " + ride.getRideRequest() );
					
				}else if(ride.getRideState().getRideState().equals(RideState.rideStatePaymentDue)){
					
					int amount = 0;

					amount = ride.getActualFare();
					
					NotificationManager.getNotificationManager().notifyPassenger(ride.getRideRequest().getPassenger(),
												"RIDE PAYMENT PROCESSING - Ride Final Fare : " + amount + "  Add Tip ? ");
					
					for(int i=0;i<5;i++){
						
						if(!ride.getRideState().getRideState().equals(RideState.rideStatePaymentDue)){
							break;
						}
						if(ride.getTipAmount() > 0){
							
							System.out.println("Tip added by Passenger for Ride - " + ride);
							break;
						}
											
						System.out.println("Waiting for Passenger to Add Tip !!! - waiting count" + i);
						if(i==5){
							System.out.println("No Tip added by Passenger for Ride - " + ride);
						}
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					synchronized(RideSubsystem.getRideSubSystem().getInProgressRides()){
						ride.ProcessPayment();
					}
				}
				
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}		
	}	
}
