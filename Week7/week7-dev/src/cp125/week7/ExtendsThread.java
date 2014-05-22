package cp125.week7;

/**
 * @author Stuart Maclean
 *
 * Create a new thread of control via the 'subclass of Thread' method.
 * The subclass overrides the <code>run</code> method.  It is started
 * via a call to <code>start</code>.
 */
public class ExtendsThread extends java.lang.Thread {
	
	static public void main( String[] args ) {
		java.lang.Thread t = new ExtendsThread();
		t.start();
	}

	@Override
	public void run() {
		while( true ) { System.out.println( getName() ); }
	}
}

// eof
