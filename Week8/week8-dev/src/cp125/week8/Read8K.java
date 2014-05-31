package cp125.week8;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * The read half of the Write/Read 8K experiment.  Shows that if we
 * want to read data over a socket and know the byte count, we must
 * never assume that a single read can get all the data.  Instead, we
 * call InputStream.read in a loop, accumulating the expected total
 * and/or looking for end-of-file (read returning 0).
 *
 * @see Write8K
 *
 */
public class Read8K {

	static public void main( String[] args ) {

		int n = 8;
		
		String usage = "Usage: " + FirstSocket.class.getName() +
			" host port n?";

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

		if( args.length >= 3 ) {
			try {
				n = Integer.parseInt( args[2] );
			} catch( NumberFormatException nfe ) {
			}
		}

		try {
			Socket s = new Socket( host, port );
			System.out.println( "Connected to : " + s );
			InputStream is = s.getInputStream();
			byte[] ba = new byte[1024*n];

			// must NOT assume all the data can arrive in one read...
			int total = 0;
			while( true ) {
				int nin = is.read( ba, total, ba.length - total );
				System.out.println( "Read " + nin );
				if( nin < 1 )
					break;
				total += nin;
			}
			s.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit(1);
		}
	}
}

// eof
