
public class Building extends AbstractBuilding
{ 
	public Building(int numFloors, int numElevators) 
	{
		super(numFloors, numElevators);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElevator callAndAwaitUp(int fromFloor) 
	{
		return null; 
	}

	@Override
	public AbstractElevator callAndAwaitDown(int fromFloor) 
	{
		return null; 
		
	}
	
}
