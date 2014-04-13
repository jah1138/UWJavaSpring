package cp125.week1;

/**
 * @author Stuart Maclean
 *
 * Writing code with star imports, and NO java.lang.  Given the ease
 * of import generation in most IDEs, this style is considered
 * sloppy/lazy and should be avoided since it doesn't inform the
 * reader what classes are actually used.
 */

import java.util.*;
import java.security.*;
import java.net.*;

public class StarImports {

	static public void main( String[] args ) {

		try {
			// collections
			List<String> l1 = new ArrayList<String>();

			// security
			MessageDigest md5 = MessageDigest.getInstance( "MD5" );

			// networking
			Socket s1 = new Socket( "www.google.com", 80 );

			// threads
			Runnable r = new Runnable() {
					public void run() {
					}
				};
			Thread t1 = new Thread( r );
			t1.start();
			System.out.println( StarImports.class );
		} catch( Exception e ) {
			System.out.println( e );
		}
	}
}

// eof
