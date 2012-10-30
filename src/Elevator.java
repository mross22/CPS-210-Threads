
public class Elevator extends AbstractElevator
{
	private EventBarrier[] ebUpList; 
	private EventBarrier[] ebDownList;
	private int numWorkers = 1000; 
	private int numWaiters = 0; 
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) 
	{
		super(numFloors, elevatorId, maxOccupancyThreshold);
		ebUpList = new EventBarrier[numFloors]; 
		ebDownList = new EventBarrier[numFloors]; 
		for(int k = 0; k < numFloors; k++)
		{
			ebUpList[k] = new EventBarrier(numWorkers); 
			ebDownList[k] = new EventBarrier(numWorkers); 
		} 
	}

	@Override
	public void OpenDoors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ClosedDoors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void VisitFloor(int floor) 
	{
		
		
		
	}

	@Override
	public void Enter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Exit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RequestFloor(int floor) {
		// TODO Auto-generated method stub
		
	}

}
