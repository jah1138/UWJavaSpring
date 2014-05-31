package cp125.week8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * Highlight how the serversocket 'backlog' constructor parameter
 * works.  Run this code, then attempt connections to its port, 52108,
 * from many clients simultaneously.  When the backlog limit is reached,
 * further clients we be refused.
 */
public class NoAcceptServer {

	static public void main( String[] args ) {
		
		try {
			int backlog = 1;
			ServerSocket ss = new ServerSocket( 52108, backlog );
			System.out.println( ss );
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit(1);
		}
		/*
		  We won't try to read/write any data, just sleep to keep
		  the connection open.  Highly likely the other side would
		  go away
		*/
		try {
			Thread.sleep( 1000L * 3600 );
		} catch( InterruptedException ie ) {
			System.err.println( ie );
			System.exit(1);
		}
	}
}

// eof
