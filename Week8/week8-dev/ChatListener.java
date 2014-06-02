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
 * A chat listener program.  Takes a single cmd line argument: a
 * listening port, and listens for a client connection.  Prints out the
 * connection details upon success or exception details on failure.
 * Then goes into a loop:
 *
 * while( true )
 *	 read a line from peer
 *   write it to user/screen
 *   read a line from the user/keyboard
 *   send line to peer
 *
 * Note how this program is single-threaded, NOT a full dual-threaded
 * architecture you would expect to see in a Chat program.
 */
public class ChatListener {

	static public void main( String[] args ) {

		String usage = "Usage: " + ChatListener.class.getName() + " port";
		
		if( args.length < 1 ) {
			System.err.println( usage );
			System.exit(1);
		}

		int port = 0;
		try {
			port = Integer.parseInt( args[0] );
		} catch( NumberFormatException nfe ) {
			System.err.println( usage );
			System.exit(1);
		}

		try {
			ServerSocket ss = new ServerSocket( port );
			System.out.println( "Listening: " + ss );
			Socket s = ss.accept();
			System.out.println( "Connected: " + s );
			chat( s );
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}

	// In the listener program, we read from peer THEN user
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
			String line = brPeer.readLine();
			pwUser.println( line );
			line = brUser.readLine();
			pwPeer.println( line );
		}
	}
}

// eof
