package cp125.week3;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * @author Stuart Maclean
 *
 * How to read lines of text from the keyboard into a Java program
 */
public class ReadConsole {

	static public void main( String[] args ) {
		InputStream source = System.in;
		InputStreamReader filter1 = new InputStreamReader( source );
		BufferedReader filter2 = new BufferedReader( filter1 );
		try {
			String line = filter2.readLine();
		} catch( IOException ioe ) {
			// recover ?  Perhaps print out or log the exception...
		}
	}
}

// eof
