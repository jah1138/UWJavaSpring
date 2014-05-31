package cp125.week8;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * A client of the StringReverser service.  Takes host and port name
 * from cmd line.  Also accepts further cmd line args, sends them all
 * to server, reads reply and prints.  If no further args, gets a line
 * from stdin, and uses that to to server instead. Like this:

 * java cp125.week8.ReverseClient host port Reverse this please
 *
 * java cp125.week8.ReverseClient host port
 *

 * By using the 'from stdin' option, we can effectively hang up the
 * server, if we just connect and do nothing.  A (primitive) denial of
 * service (DoS)
 */
public class R  everseClient {

	static public void main( String[] args ) {

		String usage = "Usage: " + ReverseClient.class.getName() +
			" host port string*";
		
		if( args.length < 2 ) {
			System.err.println( usage );
			System.exit(1);
		}

		String host = args[0];
		int port = 0;
		try {
			port = Integer.parseInt( args[1] );
		} catch( NumberFormatException nfe ) {
			System.err.println( usage );
			System.exit(1);
		}

		try {
			Socket s = new Socket( host, port );
			System.out.println( "Connected: " + s );
			OutputStream sink = s.getOutputStream();
			PrintWriter pw = new PrintWriter
				( new OutputStreamWriter( sink ) );

			if( args.length > 2 ) {
				for( int i = 2; i < args.length; i++ ) {
					pw.print( args[i] + " " );
				}
				pw.println();
			} else {
				InputStreamReader isr = new InputStreamReader( System.in );
				BufferedReader br = new BufferedReader( isr );
				String line = br.readLine();
				pw.println( line );
			}

			pw.flush();

			InputStream source = s.getInputStream();
			BufferedReader br = new BufferedReader
				( new InputStreamReader( source ) );
			String line = br.readLine();
			System.out.println( line );
			s.close();
			
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
}

// eof
