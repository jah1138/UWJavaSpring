package cp125.week1;

/**
 * @author Stuart Maclean
 *
 * Writing code with each class imported explicitly, even java.lang.
 */

// Imports are in the order of the identifiers found in the source...
import java.lang.String;
import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.net.Socket;
import java.lang.Runnable;
import java.lang.Thread;
import java.lang.Exception;
import java.lang.System;

public class ExplicitImports {
	
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
			System.out.println( ExplicitImports.class );
		} catch( Exception e ) {
			System.out.println( e );
		}
	}
}

// eof
