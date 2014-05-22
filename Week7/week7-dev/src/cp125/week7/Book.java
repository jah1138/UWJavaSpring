package cp125.week7;

/**
 *  @author Stuart Maclean
 *
 * A simple Book class, with a static 'count' member which is
 * incremented each time a Book is created.  Note how, with NO
 * synchronization around the 'count' field access, this class is not
 * 'thread-safe'.
 */

public class Book {

	public Book( String title, String author ) {
		count++;
	}

	static public int getCount() {
		return count;
	}
	
	static private int count;
}


// eof
