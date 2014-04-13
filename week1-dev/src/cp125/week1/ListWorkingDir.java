package cp125.week1;

import java.io.File;

/**
   Attempt to list the current working directory.  Run with security
   manager installed and view the results:

   java -Djava.security.manager cp125.week1.ListWorkingDir
*/

public class ListWorkingDir {

	static public void main( String[] args ) {
		// allowed, does NOT access filesystem
		File pwd = new File( "." );

		// checkAccess, DOES access filesystem
		File[] fs = pwd.listFiles();

		for( File f : fs ) {
			System.out.println( f );
		}
	}
		
}

// eof
