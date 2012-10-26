
public class EventBarrier extends AbstractEventBarrier
{
	private int numCompleted; 
	
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
				this.wait();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			} 
		}
		else // Gatekeeper is calling
		{
			while(waiters() > 0)
			{
				 // putting gatekeeper to "sleep"
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
		numCompleted++; 
		
	}

	@Override
	public synchronized int waiters() {
		return (_numWorkers - numCompleted); 
	}
	
}
