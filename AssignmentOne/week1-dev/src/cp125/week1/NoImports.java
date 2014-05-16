package cp125.week1;

/**
 * @author Stuart Maclean
 *
 * Writing code with zero use of imports.  We even fully qualify
 * classes in java.lang, for uniformity throughout the code
 */

public class NoImports {

	static public void main( java.lang.String[] args ) {

		try {
			// collections
			java.util.List<java.lang.String> l1 =
				new java.util.ArrayList<java.lang.String>();

			// security
			java.security.MessageDigest md5 =
				java.security.MessageDigest.getInstance( "MD5" );

			// networking
			java.net.Socket s1 = new java.net.Socket( "www.google.com", 80 );

			// threads
			java.lang.Runnable r = new java.lang.Runnable() {
					public void run() {
					}
				};
			java.lang.Thread t1 = new java.lang.Thread( r );
			t1.start();
		} catch( java.lang.Exception e ) {
			java.lang.System.out.println( e );
		}
	}
}

// eof
