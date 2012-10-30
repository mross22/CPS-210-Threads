
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
	public abstract AbstractElevator callAndAwaitUp(int fromFloor);

	/* Signal the elevator that we want to go down */
	public abstract AbstractElevator callAndAwaitDown(int fromFloor); 

	
}
