package cp125.week4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Stuart Maclean
 *
 * A string is Java is an array of type char, a built-in Java type.  A
 * char holds 16 bits (2 bytes).
 *
 * We define a string and inspect its length.  We then write the
 * string out to a file and inspect the file size.  How come the file
 * is 5 bytes when it seems as if it requires 5 x 2 bytes, if each
 * char is really 2 bytes.

 */

public class WriteHello {

	static public void main( String[] args ) {

		String s = "hello";
		System.out.println( s.length() );
		
		try {
			File f = new File( "hello.txt" );
			FileWriter fw = new FileWriter( f );
			fw.write( s );
			fw.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
}

// eof

		