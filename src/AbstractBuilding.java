
public abstract class AbstractBuilding {

	private int _numFloors;
	private int _numElevators;
	
	/* Other variables/data structures as needed goes here */


	public AbstractBuilding(int numFloors, int numElevators) {
		_numFloors = numFloors;
		_numElevators = numElevators;
	}

	/* Elevator rider interface (part 2): invoked by rider threads.
 	 */

	/* Signal the elevator that we want to go up */
	public abstract void CallUp(int fromFloor);

	/* Signal the elevator that we want to go down */
	public abstract void CallDown(int fromFloor); 

	/* Wait for elevator arrival and going up */
	public abstract AbstractElevator AwaitUp(int fromFloor); 

	/* Wait for elevator arrival and going down */
	public abstract AbstractElevator AwaitDown(int fromFloor);

	/* Other methods as needed goes here */
}
