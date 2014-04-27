package cp125.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Stuart Maclean
 *
 * How to create a multi-line string using StringWriter and
 * PrintWriter.  Useful when building up e.g. a menu for prompting
 * user how to enter input into some program.
 *
 * Then go onto to continually present the menu to the user and accept
 * a user choice.  Choice 0 (or eof) ends the loop.
 */
 
public class StringWriterMenus {

	static public void main( String[] args ) {

		try {
			String menu = createMenu();

			// note how we don't have to name the InputStreamReader
			BufferedReader br = new BufferedReader
				( new InputStreamReader( System.in ) );
			while( !false ) {
				// without a trailing newline, the menu won't be visible...
				System.out.print( menu );
				// ... so we flush, to force it out
				System.out.flush();

				String line = br.readLine();
				if( line == null )
					break;
				line = line.trim();
				Integer choice = Integer.parseInt( line );
				if( choice == 0 )
					break;
				// process choice
			}
		} catch( Exception e ) {
			System.err.println( e );
		}
	}

	
	static String createMenu() {
		StringWriter sink = new StringWriter();
		PrintWriter pw = new PrintWriter( sink );
		pw.println( "1: Do This" );
		pw.println( "2: Do That" );
		pw.println( "3: Do The Other" );
		pw.print( "Choice: " );
		return sink.toString();
	}
}

// eof

		
		
