
public abstract class AbstractElevator {

	private int _numFloors; 
	private int _elevatorId;
	private int _maxOccupancyThreshold;

	/* Other variables/data structures as needed goes here */

	public AbstractElevator(int numFloors, int elevatorId, int maxOccupancyThreshold) {
		_numFloors = numFloors;
		_elevatorId = elevatorId;
		_maxOccupancyThreshold = maxOccupancyThreshold;
	}

	/* Elevator control inferface: invoked by Elevator thread.
 	 */

	/* Signal incoming and outgoing riders */
	public abstract void OpenDoors(); 	

	/* When capacity is reached or maxOpenDoorTimeLimit reached or the
 	 * outgoing riders are exited and incoming riders are in. 
 	 */
	public abstract void ClosedDoors();

	/* Go to a requested floor */
	public abstract void VisitFloor(int floor);


	/* Elevator rider interface (part 1): invoked by rider threads. 
  	 */

	/* Enter the elevator */
	public abstract void Enter();
	
	/* Exit the elevator */
	public abstract void Exit();

	/* Request a destination floor once you enter */
 	public abstract void RequestFloor(int floor);	
	
	/* Other methods as needed goes here */
}
