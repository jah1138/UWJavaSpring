package cp125.week7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *  @author Stuart Maclean
 *
 * An extension of the FastLibrary program, which used threads to create
 * 10,000,000 Book objects.
 *
 * This time we attempt to add each book to an ArrayList, from many
 * threads concurrently. The list is thus the data structure shared
 * between the threads.

 * Unlike FastLibrary, which produced arbitrary results but didn't
 * exhibit any outwardly visible failure, this program can bomb. Run
 * it (many times) and observe.
 */
public class LibraryList3 {


	static public void main( String[] args ) {

        synchronized (LibraryList3.class) {

            // we'll create 10 million books, using 10 threads to do it...

            // any local variable accessed by an anonymous class must be 'final'
            final int N = 10000000;
            final int T = 10;
            final int perT = N / T;

            final List<SafeBook> books = Collections.synchronizedList(new ArrayList());
		
		/*
		  The unit of work, a 'job' in thread terminology. All threads will
		  use this one Runnable, which is perfectly OK.  Note how this
		  is an anonymous class, we provide no name for it. //No it isn't//
		*/
            Runnable r = new Runnable() {
                public void run() {
                    // accessing 'perT' in enclosing scope, must be final
                    for (int i = 1; i <= perT; i++) {
                        SafeBook b = new SafeBook("" + i, "SomeAuthor");
                        // accessing 'books' in enclosing scope, must be final
                        books.add(b);
                    }
                }
            };

            // fire off T jobs, maintaining a reference to each thread started...
            Thread[] ts = new Thread[T];
            for (int i = 1; i <= T; i++) {
                // to start a Thread, hand it a runnable and call start()
                Thread t = new Thread(r);
                t.start();
                ts[i - 1] = t;
            }

		/*
		  And wait for each job to finish.  If a thread encounters
		  an exception it does not catch, it ends.  The join is still valid.
		*/
            for (int i = 1; i <= T; i++) {
                Thread t = ts[i - 1];
                try {
                    t.join();
                } catch (InterruptedException neverInThisCode) {
                }
            }

            // and print the final book count. We expect N, but may not always get it!
            System.out.println("BookCount " + SafeBook.getCount());
        }
    }
}


// eof
