package cp125.week8;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Stuart Maclean
 *
 * Act as middle man (proxy?) in between a TCP client to server
 * connection.  Monitor traffic in both directions.  Currently we just
 * print to stdout, and know nothing of the traffic semantics.  More
 * elaborate processing could be protocol-aware.

 * Based loosely on the GUI tool 'TCPMonitor' but stripped down to a
 * basic UI.  Named after the Unix 'tee' command.
 *
 * Note how we work in 'InputStream and OutputStream' space, since all
 * we are doing is acting a middle man for the purposes of showing the
 * data, to stdout.  We have no need to work in reader/writer space.
 *
 * Example: inspect the HTTP traffic data between your web browser and
 * some arbitrary web site, say news.bbc.co.uk:
 *
 * 1 java cp125.week8.TCPTee 12345 news.bbc.co.uk 80
 *
 * 2 Point your web browser at http://localhost:12345/
 *
 */
   
public class TCPTee {

	static public void main( String[] args ) {
		
		final String usage = "Usage: " + TCPTee.class.getName() +
			" port serverHost serverPort";

		if( args.length < 3 ) {
			System.err.println( usage );
			System.exit( 1 );
		}

		// Cmd line processing...
		int listenPort = -1, serverPort = -1;
		String serverHost = null;
		try {
			listenPort = Integer.parseInt( args[0] );
			serverHost = args[1];
			serverPort = Integer.parseInt( args[2] );
		} catch( NumberFormatException nfe ) {
			System.err.println( usage );
			System.exit( 1 );
		}
		
		ServerSocket ss = null;
		try {
			ss = new ServerSocket( listenPort );
		} catch( IOException ioe ) {
			System.out.println( "Cannot listen on: " + ss );
			System.exit(-1);
		}
		
		System.out.println( "Listening: " + ss );
		while( true ) {
			try {
				Socket s = ss.accept();
				System.out.println( "Accepted : " + s );
				Worker w = new Worker( s, serverHost, serverPort );
				/*
				  Spawn new thread for the worker so we
				  can go back to listening...
				*/
				new Thread( w ).start();
			} catch( Exception e ) {
				System.err.println( e );
			}
		}
	}

	static class Worker implements Runnable {
		Worker( Socket client, String serverHost, int serverPort ) {
			this.client = client;
			this.serverHost = serverHost;
			this.serverPort = serverPort;
		}

		@Override
		public void run() {
			try {
				/*
				  We have the client connection, now connect to the
				  server...
				*/
				server = new Socket( serverHost, serverPort );
				System.out.println( "Connected: " + server );
				// Grab the in and out streams of both sockets...
				InputStream cIn = client.getInputStream();
				final OutputStream cOut = client.getOutputStream();
				final InputStream sIn = server.getInputStream();
				OutputStream sOut = server.getOutputStream();
				/*
				  So that we are fully responsive to traffic from
				  either party, need 2 threads total.  We are already
				  running in one (see main), so need one more...
				*/
				Runnable r = new Runnable() {
						public void run() {
							try {
								shunt( "S", sIn, cOut );
							} catch( IOException ioe ) {
								//System.err.println( ioe );
							}
						}
					};
				Thread t = new Thread( r );
				t.start();
				/*
				  The server->client thread now in its own thread, so
				  we just do client->server, then join the thread we
				  spawned and close down the 2 sockets...
				*/
				shunt( "C", cIn, sOut );
				try {
					t.join();
				} catch( InterruptedException ie ) {
				}
				server.close();
				client.close();
				System.out.println( "Closed: " + client );
			} catch( IOException ioe ) {
				System.err.println( ioe );
			}
		}

		/*
		 * Shunt any data read from input stream by writing it to output stream.
		 *
		 * @param id - either 'S meaning from Server, or 'C' meaning
		 * from client.
		 *
		 * @param is - the InputStream for the socket connected to either
		 * server (if id == 'S') or client
		 *
		 * @param os - the OutputStream for the socket connected to either
		 * server (if id == 'S') or client
		 */
		private void shunt( String id, InputStream is, OutputStream os )
			throws IOException {
			byte[] buf = new byte[4*1024];
			while( true ) {
				// any given read may return 1..buf.length bytes
				int nin  = is.read( buf );
				// or -1,  signifying eof
				if( nin < 1 ) 
					break;
				process( id, buf, nin );
				// write all that we have read, which is 'nin' NOT buf.length
				os.write( buf, 0, nin );
			}
			is.close();
			os.close();
		}

		/*
		  This could be anything, currently we just write traffic to
		  stdout.  We expect text-based data, which is likely the case
		  for web traffic.  Could also convert any non-printables into
		  '.' a la xxd, which would be better for binary data (add a -b
		  flag to the program perhaps?)
		*/
		void process( String id, byte[] buf, int len ) {
			System.out.println( id + " " + len );
			String s = new String( buf, 0, len );
			StringBuilder sb = new StringBuilder();
			for( int i = 0; i < s.length(); i++ ) {
				char ch = s.charAt(i);
				if( false && Character.isISOControl( ch ) )
					ch = '.';
				sb.append( ch );
			}
			System.out.println( sb );
		}

		private final Socket client;
		private final String serverHost;
		private final int serverPort;
		private Socket server;
	}
}

// eof


