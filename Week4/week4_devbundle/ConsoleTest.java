package cp125.week4.devbundle;

import java.io.Console;

/**
 * @author Stuart Maclean
 *
 * Testing the java.io.Console, introduced in JDK 1.6.  If exists,
 * will be connected to the keyboard and display.
 *
 * Certainly less involved than 'manual' I/O with readers and writers,
 * especially for reading.  We need no mention of System.in,
 * InputStreamReader nor BufferedReader.
 *
 * That said, the Console object returned by System.console is null if
 * the program is invoked with any redirected input/output (think Unix
 * isatty()).  With explicit readers and writers, we have more code to
 * write, but get a more general and thus re-usable program.
 */

public class ConsoleTest {

	static public void main( String[] args ) {

		Console c = System.console();
		if( c == null )
			return;

		// write a message to the screen
		c.printf( "Hello World\n" );

		// read a line from the keyboard, with a prompt
		String line = c.readLine( "Enter " );

		// and print it back out to the screen
		c.printf( "%s\n", line );
	}
}

// eof

		