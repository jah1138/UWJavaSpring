/**
 *  @author Stuart Maclean
 *
 * An attempt to 'speed up' the Library program.  Instead of creating
 * 10 million books in one thread, use ten threads and create one
 * million in each.  Fire off all the threads and <code>join</code>
 * them all.  If we have 2+ cpus, this program would run 'faster'
 * (i.e. complete sooner) than a single threaded equivalent.
 *
 * BUT! As written, this class together with Book.java, exhibit a 'race
 * condition', the result of which is that the value of Book.count at
 * program end is arbitrary.
 *
 * Run this program many many times and observe the printed result.
 * Even if you DO always see 10,000,000, this in no way means that the
 * next time it is run, the result will again be 10,000,000.
 */
public class FastLibrary {

	static public void main( String[] args ) {

		final int N = 10000000;
		final int T = 10;
		final int perT = N / T;

		// the unit of work, a 'job' in thread terminology
		Runnable r = new Runnable() {
				public void run() {
					for( int i = 1; i <= perT; i++ ) {
						Book b = new Book( "" + i, "SomeAuthor" );
						
					}
				}
			};
		// fire off T jobs, maintaining a reference to each thread started...
		Thread[] ts = new Thread[T];
		for( int i = 1; i <= T; i++ ) {
			// to start a Thread, hand it a runnable and call start()
			Thread t = new Thread( r );
			t.start();
			ts[i-1] = t;
		}

		// and wait for each job to finish...
		for( int i = 1; i <= T; i++ ) {
			Thread t = ts[i-1];
			try {
				t.join();
			} catch( InterruptedException neverInThisCode ) {
			}
		}

		// and print the final book count...
		System.out.println( "BookCount " + Book.getCount() );
	}
}


// eof
