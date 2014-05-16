package cp125.week1;

import java.io.File;

/**
   Attempt to list the user's home directory.  Run with security
   manager installed and view the results:

   java -Djava.security.manager cp125.week1.ListHomeDir
*/

public class ListHomeDir {

	static public void main( String[] args ) {
		// accessed checked, NOT granted by system-wide policy file
		String user = System.getProperty( "user.home" );
		
		// granted, does not access filesystem. 'new File(X)' always succeeds
		File pwd = new File( user );

		// access checked, DOES access filesystem
		File[] fs = pwd.listFiles();

		for( File f : fs ) {
			System.out.println( f );
		}
	}
		
}

// eof
