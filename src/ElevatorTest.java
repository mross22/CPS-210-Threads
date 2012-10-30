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
		if(source < target)
		{
			Elevator e = (Elevator) ElevatorTest.building.callAndAwaitUp(source); 
			e.Enter(); 
			//System.out.println("Got here");
			System.out.println("R" + id + " entered on floor " + e.currentFloor + "; Source = " + source); 
			e.RequestFloor(target); 
			e.Exit(); 
			System.out.println("R" + id + " exited on floor " + e.currentFloor + "; Target = " + target); 

		}
		else
		{
			Elevator e = (Elevator) ElevatorTest.building.callAndAwaitDown(source); 
			e.Enter(); 
			//System.out.println("Got here");
			System.out.println("R" + id + " entered on floor " + e.currentFloor + "; Source = " + source); 
			e.RequestFloor(target); 
			e.Exit(); 
			System.out.println("R" + id + " exited on floor " + e.currentFloor + "; Target = " + target); 

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
		
		building = new Building(5, 1); 
		
		
		 
		Thread r1 = new Thread(new Rider("Aditya", 1, 4));
		Thread r2 = new Thread(new Rider("Mike", 1, 3));
		Thread r3 = new Thread(building.elevator);
		
		
		r1.start(); 
		r2.start(); 
		r3.start(); 
	}
	

}
