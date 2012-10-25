
public interface EventBarrier 
{
	public void hold(); 
	public void signal(); 
	public void complete(); 
	public int waiters(); 
}
