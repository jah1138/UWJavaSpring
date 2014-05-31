package cp125.week8;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * First socket program.  Given host and port number supplied on cmd line,
 * attempt a active socket connection to that endpoint.  Observe the results.
 * No data flow, just connection logic.
 *
 * This program uses java.net.Socket object, which is how we do TCP/IP
 * programming in Java.
 *
 */
public class FirstSocket {

	static public void main( String[] args ) {

		String usage = "Usage: " + FirstSocket.class.getName() +
			" host port";

		if( args.length < 2 ) {
			System.err.println( usage );
			System.exit(1);
		}

		String host = args[0];
		int port = -1;
		try {
			port = Integer.parseInt( args[1] );
		} catch( NumberFormatException nfe ) {
			System.err.println( usage );
			System.exit(1);
		}
		
		try {
			Socket s = new Socket( host, port );
			System.out.println( "Connected to : " + s );
			s.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit(1);
		}
	}
}

// eof
