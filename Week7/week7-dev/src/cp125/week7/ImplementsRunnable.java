package cp125.week7;

/**
 * @author Stuart Maclean
 *
 * Create a new thread of control via the 'implement Runnable' method.
 * We define a new class ImplementsRunnable which implements Runnable.
 * We pass an instance of ImplementsRunnable to a Thread constructor.
 * Finally Thread.start kicks the new thread off.
 */

public class ImplementsRunnable implements java.lang.Runnable {
	static public void main( String[] args ) {
		java.lang.Runnable r = new ImplementsRunnable();
		java.lang.Thread t = new java.lang.Thread( r );
		t.start();
	}

	@Override
	public void run() {
		while( true ) {
			System.out.println( "I'm running" );
		}
	}
}

// eof

