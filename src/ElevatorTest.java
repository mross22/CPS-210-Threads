import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Create a new thread. */
class Rider implements Runnable {
	private String id;
	private int source; 
	private int target; 
	public Rider(String i, int s, int t) 
	{
		id = i; 
		source = s; 
		target = t; 
		
	}	
	public void run() 
	{
		//System.out.println("I BEGAN TO RUN"); 
		if(source < target)
		{
			Elevator e = (Elevator) ElevatorTest.building.callAndAwaitUp(source); 
			System.out.println("R" + id + " entered E" + e.myID + " on floor " + e.currentFloor + "; Source = " + source); 
			e.Enter(); 
			//System.out.println("Got here");
			e.RequestFloor(target); 
			System.out.println("R" + id + " exited E" + e.myID + " on floor " + e.currentFloor + "; Target = " + target); 
			e.Exit(); 

		}
		else
		{
			Elevator e = (Elevator) ElevatorTest.building.callAndAwaitDown(source); 
			System.out.println("R" + id + " entered " + e.myID + " on floor " + e.currentFloor + "; Source = " + source); 
			e.Enter(); 
			//System.out.println("Got here");
			System.out.println("R" + id + " entered on floor " + e.currentFloor + "; Source = " + source); 
			e.RequestFloor(target); 
			System.out.println("R" + id + " exited " + e.myID + " on floor " + e.currentFloor + "; Target = " + target); 
			e.Exit(); 

		}
		
	}
}

/* Create a new thread. */
class Gatekeeper implements Runnable {
	private String _msg;
	private EventBarrier eventBarrier;
	public Gatekeeper(String msg, EventBarrier eb) {
		_msg = msg;
		eventBarrier = eb; 
	}	
	public void run() 
	{
		eventBarrier.signal(); 
		eventBarrier.waitforevent(); 
		System.out.println("I am thread " + _msg);
	}
}
	
	
public class ElevatorTest 
{	
	public static Building building; 
	public static void main(String[] args)
	{
		// Read input from file
		FileReader fileReader;
		List<String> lines = null;
		List<Thread> riderThreads = new ArrayList<Thread>();
		try {
			fileReader = new FileReader("input.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
	        lines = new ArrayList<String>();
	        String line = null;
	        while ((line = bufferedReader.readLine()) != null) {
	            lines.add(line);
	        }
	        bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		int F = 0;
		int E = 0;
		int R = 0;
		int T = 0;
		int M = 0;

		//building = new Building(5, 3); 
		
	
        if(lines != null){
        	String[] firstLineParams = lines.get(0).split("\\s+");
        	F = Integer.parseInt(firstLineParams[0]);
        	E = Integer.parseInt(firstLineParams[1]);
        	R = Integer.parseInt(firstLineParams[2]);
        	T = Integer.parseInt(firstLineParams[3]);
        	M = Integer.parseInt(firstLineParams[4]);
        }

		building = new Building(F, E); 
		
		for(int i=1; i< lines.size();i++){
			String[] riderParams = lines.get(i).split("\\s+");
			riderThreads.add(new Thread(new Rider(riderParams[0],Integer.parseInt(riderParams[1]),Integer.parseInt(riderParams[2]))));
		}

		for(Thread rider : riderThreads){
			rider.start();
		}
		//r1.start(); 
		//r2.start(); 
		//r4.start(); 


	}
	

}
