
public class Elevator extends AbstractElevator
{
	public EventBarrier[] ebUpList; 
	public EventBarrier[] ebDownList;
	
	public int currentFloor = 0; 
	private int numWorkers = 1000; 
	private int numWaiters = 0; 
	private boolean up = true; 
	
	
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
	public void OpenDoors() 
	{
		System.out.println("Doors Opening"); 
		
	}

	@Override
	public void ClosedDoors() 
	{
		System.out.println("Doors Closing"); 
		if(up)
		{
			Building.upRequests[currentFloor] = false; 
		}
		if(up)
		{
			Building.downRequests[currentFloor] = false; 
		}
	}

	@Override
	public void VisitFloor(int floor) 
	{
		currentFloor = floor; 
		System.out.println("Just visited floor " + floor); 
		OpenDoors(); 
		if(up)
		{
			//System.out.println("SIGNALING"); 
			ebUpList[floor].signal(); 
			//System.out.println(ebUpList.toString()); 
			/*for(boolean b : Building.upRequests)
			{
				if(b)
				{
					System.out.print("true "); 
				}
				else
				{
					System.out.print("false "); 
				}
			}*/
			//ebUpList[floor].waitforevent(); 
		//	System.out.println("WAITING"); 
		}
		else
		{
			ebDownList[floor].signal(); 
			
			ebDownList[floor].waitforevent(); 
		}
		ClosedDoors();  
		
		
	}

	@Override
	public void Enter() 
	{
		if(up)
		{
			ebUpList[currentFloor].complete(); 
		}
		else
		{
			ebDownList[currentFloor].complete(); 
		}
		
	}

	@Override
	public void Exit() {
		if(up)
		{
			ebUpList[currentFloor].complete(); 
		}
		else
		{
			ebDownList[currentFloor].complete(); 
		}
		
	}

	@Override
	public void RequestFloor(int floor) 
	{
		if(up)
		{
			Building.upRequests[floor] = true; 
			ebUpList[floor].waitforevent(); 
		}
		else
		{
			Building.downRequests[floor] = true;
			ebDownList[floor].waitforevent();
		}
	}

	@Override
	public void run() 
	{
		while(true)
		{
			if(up)
			{
				for(int k = 0; k < Building.upRequests.length; k++)
				{
				//	System.out.println("--- I am on Floor " + k); 
 
					if(Building.upRequests[k])
					{
						//System.out.println("Wooo"); 
						VisitFloor(k); 
						//System.out.println("Wooo2"); 

					}
				} 
			}
			else
			{
				for(int k = Building.downRequests.length - 1; k >= 0; k--)
				{
					if(Building.downRequests[k])
					{
						VisitFloor(k); 
					}
				}
			}
			up = !up; 
		}
		
	}

}
