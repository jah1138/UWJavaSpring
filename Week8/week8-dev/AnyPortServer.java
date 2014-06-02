import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * Given minPort = 1024 or args[0] and maxPort = 65535 or args[1],
 * spawn a new thread for each 'port'.  The thread listens on a
 * ServerSocket for clients to connect, and presumably would exchange
 * data with those clients.
 *
 * This program just demonstrates that a single JVM can listen on 2+
 * server sockets simultaneously.  (Some systems still use inetd,
 * which listens on many ports simultaneously, this program does this
 * too)

 * Can use e.g. FirstSocket from this bundle to connect to any of our
 * open ports.
 *
 */
public class AnyPortServer {

	static public void main( String[] args ) {

		/*
		  On Unix (including MacOS?) only superuser can bind/listen
		  on ports < 1024
		*/
		int min = 1024;
		if( args.length > 0 ) {
			min = Integer.parseInt( args[0] );
		}
		int max = 65535;
		if( args.length > 1 ) {
			max = Integer.parseInt( args[1] );
		}
		
		for( int i = min; i <= max; i++ ) {
			Worker w = new Worker( i );
			new Thread( w ).start();
		}
	}

	// We use a Runnable to achieve multipl threads...
	static class Worker implements Runnable {
		Worker( int port ) {
			this.port = port;
		}

		@Override
		public void run() {
			try {
				ServerSocket ss = new ServerSocket( port );
				System.out.println( "Listening: " + ss );
				while( true ) {
					/*
					  A serialized, i.e. NOT multi-threaded, service.
					  While we are interacting with one client, we
					  cannot accept a new client
					*/
					Socket s = ss.accept();
					System.out.println( "Connected: " + s );
					process( s );
					s.close();
				}
			} catch( IOException ioe ) {
				System.err.println( ioe );
			}
		}

		private void process( Socket s ) throws IOException {
			if( port == 80 ) {
				// do some web stuff perhaps
			}
			if( port == 25 ) {
				// do some email/smtp stuff
			}
			if( port == 6881 ) {
				// BitTorrent
			}
			// etc
		}

		private final int port;
	}
}

// eof
