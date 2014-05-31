/**
 * @author Stuart Maclean
 *
 * Create a new thread of control via the 'subclass of Thread' method.
 * The subclass overrides the <code>run</code> method.  It is started
 * via a call to <code>start</code>.  Observe the 'interleaved
 * execution' of the main thread and the new thread on stdout.
 */
public class ConcurrentThreads {
	
	static public void main( String[] args ) {

		Thread t = new MyThread();
		t.start();

		/*
		  Though the run method of MyThread never returns, because it
		  was started in a new thread, control reaches here. Without
		  threads, that would never be the case.
		*/
		  
		String name = Thread.currentThread().getName();
		while( true ) {
			System.out.println( name );
		}
	}
	
	static class MyThread extends Thread {
		public void run() {
			String name = getName();
			while( true ) {
				System.out.println( name );
			}
		}
	}
}

// eof
