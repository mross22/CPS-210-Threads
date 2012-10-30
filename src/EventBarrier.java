
public class EventBarrier extends AbstractEventBarrier
{
	private int numCompleted; 
	public int numWaiters = 0; 
	
	public EventBarrier(int numWorkers) 
	{
		super(numWorkers);

	}

	@Override
	public synchronized void waitforevent() 
	{
		// Minstrel is calling
		if(!signal)
		{
			try 
			{
				numWaiters++; 
				this.wait();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			} 
		}
		else // Gatekeeper is calling
		{
			
			//signal = false; 
			return; 
			
		}
		
	}

	@Override
	public synchronized void signal()
	{
		numCompleted = 0; 
		signal = true; 
		this.notifyAll(); 
		while(waiters() > 0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		signal = false;
		
	}

	@Override
	public synchronized void complete() 
	{
		//System.out.println("Num Completed = " + numCompleted); 
		//numCompleted++; 
		//System.out.println("Waiting = " + numWaiters); 
		numWaiters--; 
		
		/*if(numCompleted == _numWorkers)
		{
			this.notifyAll(); 
		}*/
		if(numWaiters == 0)
		{
			this.notifyAll(); 
		}
		
	}

	@Override
	public synchronized int waiters() {
		return numWaiters; 
	}
	
}
