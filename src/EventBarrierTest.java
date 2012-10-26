/* Create a new thread. */
class Minstrel implements Runnable {
	private String _msg;
	private EventBarrier eventBarrier; 
	public Minstrel(String msg, EventBarrier eb) {
		_msg = msg;
		eventBarrier = eb; 
	}	
	public void run() {
		eventBarrier.waitforevent(); 
		System.out.println("I am thread " + _msg);
		eventBarrier.complete(); 

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
	
	
public class EventBarrierTest 
{	
	public static void main(String[] args)
	{
		EventBarrier eb = new EventBarrier(5); 
		Thread g = new Thread(new Gatekeeper("Gatekeeper", eb));
		Thread m1 = new Thread(new Minstrel("Minstrel 1", eb));
		Thread m2 = new Thread(new Minstrel("Minstrel 2", eb));
		Thread m3 = new Thread(new Minstrel("Minstrel 3", eb));
		Thread m4 = new Thread(new Minstrel("Minstrel 4", eb));
		Thread m5 = new Thread(new Minstrel("Minstrel 5", eb));
		
		m1.start(); 
		m2.start();
		m3.start();
		m4.start();
		m5.start();
		g.start();
		
	}
	

}
