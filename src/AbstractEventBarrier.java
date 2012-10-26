
public abstract class AbstractEventBarrier {

	/* An EventBarrier is created for a specific number of "worker"
 	 * threads (minstrels in the analogy) and one "controlling" 
 	 * (gatekeeper) thread.
 	 */
	private int _numWorkers;

	/* At minimum, the constructor should at least take one argument,
 	 * which is the number of worker threads.
 	 * Q: What are the other possible arguments which we may want to
 	 * initialize in the construtor?
 	 */
	public AbstractEventBarrier(int numWorkers) {
		_numWorkers = numWorkers;
	}

	/* Method signatures 
 	 */

	/* Wait until an event is signaled. Return immediately if already in
 	 * the signaled state.
 	 * Q: Who should invoke waitforevent()? 
 	 * A: Gatekeeper, Worker threads
 	 * Q: When does waitforevent() return? 
 	 * A: When all the worker threads signaled on the event.
 	 */
	public abstract void waitforevent();

	/* Signal the event and block until all threads that wait for this
 	 * event have responded. The EventBarrier returns to an unsignaled state
 	 * before signal() returns.
 	 * Q: Who should invoke signal()?
 	 * A: Gatekeeper
 	 * Q: When?
 	 * A: After waitforevent() returns. Why? No more hints.
 	 */	
	public abstract void signal();
	
	/* Indicate that the calling thread has finished responding to a
 	 * signaled event, and block until all other threads that wait for 
 	 * this event have also responded.
 	 * Q: Who should invoke complete()?
 	 * A: From the analogy, minstrels, i.e., worker threads.
 	 */
	public abstract void complete();

	/* Return a count of threads that are waiting for the event or that
 	 * have not responded yet.
 	 * Q: Who should invoke waiters()?
 	 * A: Gatekeeper
 	 */
	public abstract int waiters();
}


/* Hints for creating a EventBarrier test program:
 * Recreate the analogy of minstrels crossing the drawbrige to enter the castle 
 * using the synchronization primitives provided by the EventBarrier.
 * 
 * Input: numWorker threads (from the command line)
 * Process: (1) Initially, the minstrels will wait for the drawbridge to come 
 * 		down (invoking waitforevent() in our case)
 * 	    (2) The gatekeeper will signal() to wake the minstrels
 * 	    (3) Then gatekeeper will block using waitforevent()
 *	    (4) Each minstrel enters the drawbridge and invokes complete()
 *	        after they have crossed the bridge.
 *	    (5) When all the minstrels crossed the drawbridge, waitforevent() 
 *	        will return.
 *	    (6) Go to (1), i.e., the process repeats.
 *	    
 * */

