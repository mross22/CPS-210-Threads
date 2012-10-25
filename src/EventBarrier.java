
public interface EventBarrier 
{
	public void wait(); 
	public void signal(); 
	public void complete(); 
	public int waiters(); 
}
