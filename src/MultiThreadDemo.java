/* Create a new thread. */
class NewThread implements Runnable {
	private String _msg;
	public NewThread(String msg) {
		_msg = msg;
	}	
	public void run() {
		System.out.println("I am thread " + _msg);
	}
}

/* A demo program to create multiple threads. */
class MultiThreadDemo {
	public static void main(String args[]) {
		System.out.println("Creating two threads ping and pong....");
		Thread t1 = new Thread(new NewThread("ping")); /* create a first thread */
		Thread t2 = new Thread(new NewThread("pong")); /* create a second thread */
		/* start the threads */
		System.out.println("Starting the thread execution....");
		t1.start();
		t2.start();

		System.out.println("Waiting for threads to complete....");
		try {
			t1.join();
			t2.join();
		} catch(InterruptedException e) {
			System.out.println("Thread interrupted.");
		}
		System.out.println("All threads completed.");
	}
}
