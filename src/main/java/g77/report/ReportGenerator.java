package g77.report;

import java.util.concurrent.ConcurrentLinkedQueue;

import g77.ride.Ride;
import g77.ride.RideSubsystem;

public class ReportGenerator {
	
	private static ReportGenerator reportGenerator =null;
	
	private ReportGenerator(){
		
	}
	
	public static ReportGenerator getReportGeneratorInstance(){
		if(reportGenerator==null){
			reportGenerator = new ReportGenerator(); 
		}
		return reportGenerator;
	}
	
	public ConcurrentLinkedQueue<Ride> getAllRides(){
		
		ConcurrentLinkedQueue<Ride> rides = new ConcurrentLinkedQueue<Ride>(RideSubsystem.getRideSubSystem().getRequestedRides());	
		rides.addAll(RideSubsystem.getRideSubSystem().getInProgressRides());
		rides.addAll(RideSubsystem.getRideSubSystem().getCompletedRides());
		
		return rides;
	}
	
	
	

}
