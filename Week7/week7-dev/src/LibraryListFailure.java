import java.util.ArrayList;
import java.util.List;

/**
 *  @author Stuart Maclean
 *
 * An extension of the FastLibrary program, which used threads to
 * create 10,000,000 Book objects, or try to at least. We saw it
 * produce somewhat arbitary book counts, few of which were actually
 * the desired 10,000,000.
 *
 * This time we attempt to add each book to an ArrayList, from many
 * threads concurrently. The list is thus the data structure shared
 * between the threads.

 * Unlike FastLibrary, which produced arbitrary results but didn't
 * exhibit any outwardly visible failure, this program can bomb. Run
 * it (many times) and observe.
 */
public class LibraryListFailure {

	static public void main( String[] args ) {

		// we'll create 10 million books, using 10 threads to do it...
		
		// any local variable accessed by an anonymous class must be 'final'
		final int N = 10000000;
		final int T = 10;
		final int perT = N / T;

		final List<Book> books = new ArrayList<Book>();
		
		/*
		  The unit of work, a 'job' in thread terminology. All threads
		  will use this one Runnable, which is perfectly OK.  Note how
		  this is an anonymnous class, we provide no name for it.
		*/
		Runnable r = new Runnable() {
				public void run() {
					// accessing 'perT' in enclosing scope, must be final
					for( int i = 1; i <= perT; i++ ) {
						Book b = new Book( "" + i, "SomeAuthor" );
						// accessing 'books' in enclosing scope, must be final
						books.add( b );
					}
				}
			};
		
		// fire off T jobs, maintaining a reference to each thread started...
		Thread[] ts = new Thread[T];
		for( int i = 1; i <= T; i++ ) {
			// to start a Thread, hand it a runnable and call start()
			Thread t = new Thread( r );
			ts[i-1] = t;
			t.start();
		}

		/*
		  And wait for each job to finish.  If a thread encounters an
		  exception it does not catch, it ends.  The join is still
		  valid.
		*/
		for( int i = 1; i <= T; i++ ) {
			Thread t = ts[i-1];
			try {
				t.join();
			} catch( InterruptedException neverInThisCode ) {
			}
		}

		// Print the final book count. We expect N, but may not always get it!
		System.out.println( "BookCount " + Book.getCount() );
	}
}


// eof
