package cp125.week8;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 *
 * Listen on a free port for a connection.  Then use a single
 * OutputStream.write call to write some nominal data (all zeros) to
 * the client.  Default is 8K, but can request bigger buffer via
 * args[1].
 *
 * Used in conjunction with Read8K to show that the reader must NOT
 * assume that just because we did one write that all the data can be
 * read with one read.
 *
 * @see Read8K
 */
public class Write8K {

	static public void main( String[] args ) {

		int n = 8;
		
		String usage = "Usage: " + Write8K.class.getName() + " port n?";

		if( args.length < 1 ) {
			System.err.println( usage );
			System.exit(1);
		}
		
		int port = -1;
		try {
			port = Integer.parseInt( args[0] );
		} catch( NumberFormatException nfe ) {
			System.err.println( usage );
			System.exit(1);
		}
		

		if( args.length >= 2 ) {
			try {
				n = Integer.parseInt( args[1] );
			} catch( NumberFormatException nfe ) {
			}
		}

		try {
			ServerSocket ss = new ServerSocket( port );
			System.out.println( "Listening on : " + ss );
			// The accept call blocks us until a client program connects...
			Socket s = ss.accept();
			System.out.println( "Connected to : " + s );

			OutputStream os = s.getOutputStream();
			byte[] ba = new byte[1024*n];
			os.write( ba );
			s.close();
			ss.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit(1);
		}
	}
}

// eof
