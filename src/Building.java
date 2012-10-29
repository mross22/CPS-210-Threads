
public class Building extends AbstractBuilding
{
	private EventBarrier[] ebList; 
	public Building(int numFloors, int numElevators) 
	{
		super(numFloors, numElevators);
		ebList = new EventBarrier[numFloors]; 
		for(int k = 0; k < ebList.length; k++)
		{
			ebList[k] = new EventBarrier(1); 
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void CallUp(int fromFloor) 
	{
		ebList[fromFloor].signal(); 
		
	}

	@Override
	public void CallDown(int fromFloor) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractElevator AwaitUp(int fromFloor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractElevator AwaitDown(int fromFloor) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
