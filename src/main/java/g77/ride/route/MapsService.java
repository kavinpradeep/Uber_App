package g77.ride.route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import g77.common.entities.Location;

public class MapsService {
	
	Route route= new Route();
	
	private int mode=0;
	private List<Location> myMap = new ArrayList<Location>();
	boolean nextloop=true;
	
	private ArrayList<Integer> myMapList = new ArrayList<Integer>();
	private ArrayList<Integer> peekList = new ArrayList<Integer>();
	
	private Map<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();;
	
	private Map<Integer,Double> lati = new HashMap<Integer,Double>();
	private Map<Integer,Double> longi = new HashMap<Integer,Double>();
	private Map<String,Integer> cit = new HashMap<String,Integer>();
	
	MapsService()
	{
		this.route.setDistance(0);
		
		/**************Create Map *************/
		
		myMapList.add(2);
		myMapList.add(4);
		myMapList.add(3);
		myMapList.add(10);
		map.put(1, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(1);
		myMapList.add(3);
		myMapList.add(7);
		myMapList.add(10);
		map.put(2, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(4);
		myMapList.add(1);
		myMapList.add(2);
		myMapList.add(7);
		myMapList.add(8);
		myMapList.add(9);
		map.put(3, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(5);
		myMapList.add(1);
		myMapList.add(3);
		myMapList.add(6);
		map.put(4, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(4);
		myMapList.add(11);
		myMapList.add(14);
		map.put(5, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(4);
		myMapList.add(3);
		map.put(6, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(2);
		myMapList.add(3);
		myMapList.add(8);
		map.put(7, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(7);
		myMapList.add(3);
		myMapList.add(9);
		map.put(8, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(3);
		myMapList.add(8);
		map.put(9, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(1);
		myMapList.add(11);
		myMapList.add(2);
		map.put(10, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(10);
		myMapList.add(5);
		myMapList.add(12);
		map.put(11, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(11);
		myMapList.add(13);
		map.put(12, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(12);
		myMapList.add(14);
		map.put(13, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		myMapList.add(13);
		myMapList.add(5);
		map.put(14, new ArrayList<Integer>(myMapList));
		myMapList.clear();
		
		lati.put(1, 37.3688);longi.put(1, -122.0313);cit.put("Sunnyvale",1);
		lati.put(2, 37.3230);longi.put(2, -122.0322);cit.put("Cupertino",2);
		lati.put(3, 37.3230);longi.put(3, -121.8863);cit.put("San Jose",3);
		lati.put(4, 37.4323);longi.put(4, -121.8996);cit.put("Milpitas",4);
		lati.put(5, 37.5413);longi.put(5, -121.9886);cit.put("Fremont",5);
		lati.put(6, 37.3708);longi.put(6, -121.8207);cit.put("Alum Rock",6);
		lati.put(7, 37.2358);longi.put(7, -121.9624);cit.put("Los Gatos",7);
		lati.put(8, 38.4148);longi.put(8, -90.8140);cit.put("Robertsville",8);
		lati.put(9, 37.0058);longi.put(9, -121.5683);cit.put("Gilroy",9);
		lati.put(10, 37.3861);longi.put(10, -122.0839);cit.put("Mountain View",10);
		lati.put(11, 37.4419);longi.put(11, -122.1430);cit.put("Palo Alto",11);
		lati.put(12, 37.5630);longi.put(12, -122.3255);cit.put("San Mateo",12);
		lati.put(13, 37.6688);longi.put(13, -122.0808);cit.put("Hayward",13);
		lati.put(14, 37.5934);longi.put(14, -122.0438);cit.put("Union City",14);
		
		/*************Create Map End**************/
		
		peekList.add(1);
			
	}
	
	public Location getlatlong(String city)
	{
		
		Location l = new Location(city);
		l.updateLocation(lati.get(cit.get(city)), longi.get(cit.get(city)));
		return l;
		
	}
	
	Route getRoute(int mode,String source, String destination)
	{
		this.mode=mode;
//		source = "San Jose";
//		destination = "Palo Alto";
		double dest_long;
		double dest_lat;
		int dest_no,closer;
		
		dest_lat=lati.get(cit.get(destination));
		dest_long=longi.get(cit.get(destination));
		dest_no=cit.get(destination);
		
		myMapList = map.get(cit.get(source));
		myMap.add(this.getlatlong(source));
		
		do{
			
			this.route.setDistance(this.route.getDistance()+1);
			
			if(myMapList.contains(dest_no))
				nextloop=false; //Destination Reached
			else
			{
				closer=this.findClose(dest_lat,dest_long,myMapList);
				myMapList = map.get(closer);
				
				//////////////////--Set to Location List--////////////
				
				Iterator<Entry<String, Integer>> iter = cit.entrySet().iterator();
				while (iter.hasNext()) {
				    Entry<String, Integer> entry = iter.next();
				    if (entry.getValue().equals(closer)) {
				        String city = entry.getKey();
				        myMap.add(this.getlatlong(city));
				    }
				}
				
				////////////////////////////////////////////////////
				
			}
			
		}while(nextloop);
		
		myMap.add(this.getlatlong(destination));
		
//		System.out.println("Distance : "+this.route.getDistance());		
//		System.out.println(myMap);
		
		this.route.setLocationList(myMap);		
		return this.route;
	}
	
	int findClose(double dest_lat,double dest_long,ArrayList<Integer> my)
	{
		
		int no,i=0;
		double src_lat,src_long,dist;
		double min_dist=10000;
		int min_dest=0;
		
		for (i = 0; i < my.size(); i++) 
		{
			no=my.get(i);
			src_lat=lati.get(no);
			src_long =longi.get(no);
			dist=Math.pow(Math.abs(src_lat-dest_lat), 2)+Math.pow(Math.abs(src_long-dest_long), 2);
			if(dist<min_dist)
			{
				if(this.mode==2 && peekList.contains(no))
					continue;
				else
				{
					min_dist=dist;
					min_dest=no;
				}
			}
		}
		
		return min_dest;
	}
	
	double findDistance(double src_lat,double src_long,double dest_lat,double dest_long)
	{
		double dlat = dest_lat-src_lat;
		double dlon = dest_long-src_long;
		double a,c,d;
		
		a = (Math.pow((Math.sin(Math.abs(dlat)/2)),2) + Math.cos(Math.abs(src_lat)) * Math.cos(Math.abs(dest_lat)) * Math.pow((Math.sin(Math.abs(dlon)/2)),2)) ;
		//System.out.println("A:"+a);
		//System.out.println(Math.pow((Math.sin(Math.abs(dlat)/2)),2));
		c=2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
		//System.out.println("C:"+c);
		d=6371*c;
		//System.out.println("D:"+d);		
		return d;
	}
}
