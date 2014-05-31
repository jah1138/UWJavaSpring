package cp125.week8;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * A general purpose socket connection program.  Takes two cmd line
 * arguments: a host and and port, and attempts a connection.  Prints
 * out the connection details upon success or execption details on failure.
 * Does NOT try to move any data.
 */
public class Connector {

	static public void main( String[] args ) {

		String usage = "Usage: " + Connector.class.getName() + " host port";
		
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
			System.out.println( s );
			Thread.sleep( 1000L * 3600 );
		} catch( IOException ioe ) {
			System.err.println( ioe );
		} catch( InterruptedException ie ) {
			System.err.println( ie );
		}
	}
}

// eof
