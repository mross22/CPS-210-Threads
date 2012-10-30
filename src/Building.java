
public class Building extends AbstractBuilding
{ 
	public static boolean[] upRequests; 
	public static boolean[] downRequests; 
	public static Elevator elevator; 
	public Building(int numFloors, int numElevators) 
	{
		super(numFloors, numElevators);
		upRequests = new boolean[numFloors]; 
		downRequests = new boolean[numFloors]; 
		elevator = new Elevator(numFloors, 1, 10000); 
		for(int k = 0; k < numFloors; k++)
		{
			upRequests[k] = false; 
			downRequests[k] = false; 
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public synchronized AbstractElevator callAndAwaitUp(int fromFloor) 
	{
		System.out.println("--- Call And Await Up from " + fromFloor); 
		upRequests[fromFloor] = true; 
		elevator.ebUpList[fromFloor].waitforevent(); 
		return elevator; 
	}

	@Override
	public synchronized AbstractElevator callAndAwaitDown(int fromFloor) 
	{
		downRequests[fromFloor] = true; 
		elevator.ebDownList[fromFloor].waitforevent(); 
		return elevator; 
		
	}
	
}
