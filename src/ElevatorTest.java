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
			System.out.println("R" + id + " entered " + e.myID + " on floor " + e.currentFloor + "; Source = " + source); 
			e.Enter(); 
			//System.out.println("Got here");
			e.RequestFloor(target); 
			System.out.println("R" + id + " exited " + e.myID + " on floor " + e.currentFloor + "; Target = " + target); 
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
		
		building = new Building(5, 3); 
		
		
		 
		Thread r1 = new Thread(new Rider("Aditya", 3, 1));
		//System.out.println("ADITYA THREAD CREATED"); 
		Thread r2 = new Thread(new Rider("Mike", 1, 3));
		//System.out.println("MIKE THREAD CREATED"); 
		Thread r4 = new Thread(new Rider("Bobby", 2, 3));
		Thread r5 = new Thread(new Rider("Laams", 4, 2)); 
		//System.out.println("Bobby THREAD CREATED"); 
		
		
		
		r1.start(); 
		//r2.start(); 
		//r4.start(); 
		//r5.start(); 
 
	}
	

}
