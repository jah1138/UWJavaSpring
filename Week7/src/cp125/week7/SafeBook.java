package cp125.week7;

/**
 *  @author Stuart Maclean
 *
 * A thread-safe fix for the non-thread-safe Book class.  All access
 * to the static field 'count' is protected via the synchronized
 * keyword, which results in use of the monitor/lock associated with
 * the SafeBook.class object.
 */
public class SafeBook {

	
	public SafeBook(String title, String author) {
		incCount();
	}

	/**
	 * The use of 'synchronized' in a static method uses the class
	 * object's monitor/lock to guarantee exclusive access.  Note that
	 * incCount is the ONLY method in SafeBook which updates count, so
	 * only it requires the synchronized keyword.
	*/
	static synchronized void incCount() {
		count++;
	}
	
	static public int getCount() {
		return count;
	}
	
	static private int count;
}


// eof
