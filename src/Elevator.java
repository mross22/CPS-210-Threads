
public class Elevator extends AbstractElevator
{
	public EventBarrier[] ebUpList; 
	public EventBarrier[] ebDownList;
	public boolean[] upRequests; 
	public boolean[] downRequests; 
	
	public int elevatorId;
	public int currentFloor = 0; 
	private int numWorkers = 1000; 
	private int numWaiters = 0; 
	public int myID; 
	public boolean up = true; 
	
	
	public Elevator(int numFloors, int elevatorId, int maxOccupancyThreshold) 
	{
		super(numFloors, elevatorId, maxOccupancyThreshold);
		myID = elevatorId; 
		ebUpList = new EventBarrier[numFloors]; 
		ebDownList = new EventBarrier[numFloors]; 
		upRequests = new boolean[numFloors]; 
		downRequests = new boolean[numFloors];
		for(int k = 0; k < numFloors; k++)
		{
			ebUpList[k] = new EventBarrier(numWorkers); 
			ebDownList[k] = new EventBarrier(numWorkers); 
			upRequests[k] = false; 
			downRequests[k] = false;
		} 
	}

	@Override
	public void OpenDoors() 
	{
		System.out.println("Elevator " + myID + " doors opening on F" + currentFloor); 		

	}

	@Override
	public void ClosedDoors() 
	{
		System.out.println("Elevator " + myID + " doors closing on F" + currentFloor); 

		if(up)
		{
			this.upRequests[currentFloor] = false; 
		}
		if(up)
		{
			this.downRequests[currentFloor] = false; 
		}
	}

	@Override
	public void VisitFloor(int floor) 
	{
		currentFloor = floor; 

		//System.out.println("Just visited floor " + floor); 
		System.out.println("E" + myID + " moves " + ((up) ? "up" : "down") + " to F" + floor);

		OpenDoors(); 
		if(up)
		{
			System.out.println("This is up");  

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
			System.out.println("This is down");  

			ebDownList[floor].signal(); 
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
			this.upRequests[floor] = true; 
			ebUpList[floor].waitforevent(); 
		}
		else
		{
			this.downRequests[floor] = true;
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
				for(int k = 0; k < this.upRequests.length; k++)
				{
				//	System.out.println("--- I am on Floor " + k); 
 
					if(this.upRequests[k])
					{
						//System.out.println("Wooo"); 
						VisitFloor(k); 
						//System.out.println("Wooo2"); 

					}
				} 
			}
			else
			{
				for(int k = this.downRequests.length - 1; k >= 0; k--)
				{
					if(this.downRequests[k])
					{
						VisitFloor(k); 
					}
				}
			}
			up = !up; 
		}
		
	}

}
