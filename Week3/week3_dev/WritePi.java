package cp125.week3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Stuart Maclean
 *
 * Define the string consisting of a single character "pi",
 * i.e. 3.14159, a letter in the Greek alphabet.  This character has
 * no ASCII representation, but DOES have a Unicode one.
 *
 * Then simply print it out, to stdout and a file.  How big is the
 * file?  Is it one byte?  More? What does it depend on?
 */
public class WritePi {

	static public void main( String[] args ) {
		String pi = "\u03c0";

		System.out.println( pi );

		try {
			FileWriter fw = new FileWriter( "pi.txt" );
			fw.write( pi );
			fw.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
}

// eof
