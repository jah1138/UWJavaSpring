/**
 * @author Stuart Maclean
 *
 * Create 10 million Book objects in a single main thread.  Class Book
 * has a static counter field, incremented in each constructor call.
 */

public class Library {

	static public void main( String[] args ) {

		final int N = 10000000;

		for( int i = 1; i <= N; i++ ) {
			Book b = new Book( "" + i, "SomeAuthor" );
		}
		System.out.println( "BookCount " + Book.getCount() );
	}
}


// eof
