package cp125.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * A chat connector program.  Takes two cmd line arguments: a host and
 * and port, and attempts a connection.  Prints out the connection
 * details upon success or exception details on failure.  Then goes into a loop:
 *
 * while( true )
 *   read a line from the user/keyboard
 *   send line to peer
 *   read a line from peer
 *   write it to user/screen
 *
 * Note how this program is single-threaded, NOT a full dual-threaded
 * architecture you would expect to see in a Chat program.
 */
public class ChatConnector {

	static public void main( String[] args ) {

		String usage = "Usage: " + ChatConnector.class.getName() + " host port";
		
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
			chat( s );
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
	
	// In the connector program, we read from user THEN peer
	static void chat( Socket s ) throws IOException {
		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		
		boolean autoFlush = true;

		// objects for reading,writing text over the socket
		BufferedReader brPeer = new BufferedReader
			( new InputStreamReader( is ) );
		PrintWriter pwPeer = new PrintWriter
			( new OutputStreamWriter( os ), autoFlush );
		
		// objects for reading,writing text at the user console
		BufferedReader brUser = new BufferedReader
			( new InputStreamReader( System.in ) );
		PrintWriter pwUser = new PrintWriter( System.out, autoFlush );

		while( true ) {
			String line = brUser.readLine();
			pwPeer.println( line );
			line = brPeer.readLine();
			pwUser.println( line );
		}
	}
}

// eof
