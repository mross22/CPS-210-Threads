
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
			try 
			{
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			signal = false; 
			return; 
			
		}
		
	}

	@Override
	public synchronized void signal() 
	{
		numCompleted = 0; 
		signal = true; 
		this.notifyAll(); 
	}

	@Override
	public synchronized void complete() 
	{
		System.out.println("Num Completed = " + numCompleted); 
		numCompleted++; 
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
		return (_numWorkers - numCompleted); 
	}
	
}
