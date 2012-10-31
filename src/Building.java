
public class Building extends AbstractBuilding
{ 
	
	public static Elevator[] elevators; 
	//public static Elevator elevator; 
	public Building(int numFloors, int numElevators) 
	{
		super(numFloors, numElevators);
		elevators = new Elevator[numElevators];  
		for(int k = 0; k < numElevators; k++)
		{
			elevators[k] = new Elevator(numFloors, k, 10000); 
			Thread r3 = new Thread(elevators[k]); 
			r3.start(); 
		}
		//elevator = new Elevator(numFloors, 1, 10000); 

		// TODO Auto-generated constructor stub
	}

	@Override
	public AbstractElevator callAndAwaitUp(int fromFloor) 
	{
		//System.out.println("--- Call And Await Up from " + fromFloor); 
		Elevator closestElevator = null; 
		while(closestElevator == null)
		{
			for(Elevator e : elevators)
			{
				if(e.up && (e.currentFloor < fromFloor))
				{
					if(closestElevator == null)
					{
						closestElevator = e; 
					}
					else if(e.currentFloor > closestElevator.currentFloor)
					{
						closestElevator = e; 
					}		
					
				}
			}
		}
		closestElevator.upRequests[fromFloor] = true; 
		closestElevator.ebUpList[fromFloor].waitforevent(); 
		return closestElevator; 
	}

	@Override
	public AbstractElevator callAndAwaitDown(int fromFloor) 
	{
		Elevator closestElevator = null; 
		System.out.println("This is happening");  

		while(closestElevator == null)
		{
			for(Elevator e : elevators)
			{
				if(!e.up && (e.currentFloor > fromFloor))
				{
					if(closestElevator == null)
					{
						closestElevator = e; 
					}
					else if(e.currentFloor < closestElevator.currentFloor)
					{
						closestElevator = e; 
					}		
					
				}
			}
		}
		closestElevator.downRequests[fromFloor] = true; 
		closestElevator.ebDownList[fromFloor].waitforevent(); 
		System.out.println("This is happening");  

		return closestElevator;  
		
	}
	
}
