package g77.ride;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import g77.common.entities.Driver;
import g77.common.entities.Location;

public class DriverTracker implements Runnable {

	private Thread t;
	private String ThreadName = "DriverTracker";
	boolean isRunning = true;
	
	
	public synchronized boolean isRunning() {
		return isRunning;
	}

	public synchronized void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void run() {
		
		ProcessDriverState();
		
	}
	
	public void start(){
		
	      System.out.println("Starting " + ThreadName );
	      if (t == null)
	      {
	         t = new Thread (this, ThreadName);
	         t.start ();
	      }
	}
	
	
	private void ProcessDriverState(){
		
		while(isRunning){
			
			//TODO - Tracking the driver location and update it.
			
//			Iterator<Entry<Driver, Location>> iterator = RideSubsystem.getRideSubSystem().getDriverlocations().entrySet().iterator();
//			while(iterator.hasNext()) {
//			        Map.Entry<Driver, Location> entry = iterator.next();
//			        Driver d = entry.getKey();			        
//			        entry.getValue().updateLocation(0, 0);
//			 }
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
	}

}
