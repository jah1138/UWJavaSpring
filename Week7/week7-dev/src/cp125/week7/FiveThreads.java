package cp125.week7;

/**
 * @author Stuart Maclean
 *
 * Create 5 new threads of control via the 'implement Runnable'
 * method.  Each just prints its 'name', assigned via the constructor,
 * forever, sleeping some random number of milliseconds (0-1000)
 * between each print out.
 *
 * Try commenting out the Thread.sleep() call and observe the output.
 * Do all the threads still get to run?
 */

public class FiveThreads {

	static public void main( String[] args ) {
		int N = 5;
		
		for( int i = 1; i <= N; i++ ) {
			Runnable r = new ICanRun( "R" + i );
			Thread t = new Thread( r );
			t.start();
		}
	}

	static class ICanRun implements Runnable {
		public ICanRun( String name ) {
			this.name = name;
		}
		@Override
		public void run() {
			while( true ) {
				System.out.println( name );
				try {
					Thread.sleep( (long)(Math.random() * 1000) );
				} catch( InterruptedException ie ) {
				}
			}
		}
		private final String name;
	}
}

// eof

