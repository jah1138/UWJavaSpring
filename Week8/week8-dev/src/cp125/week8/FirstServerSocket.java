package cp125.week8;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * First server socket program.  Given a port number supplied on cmd
 * line, attempt a passive wait on that server socket.  When (if!) a
 * client ever connects, print out the connection details.  No data flow,
 * just connection stuff.

 * This program uses java.net.ServerSocket and java.net.Socket objects,
 * which is how we do TCP/IP programming in Java.

 * Possible errors:

 * 1 No permissions to 'listen' on the port
 * 2 port out of range
 * 3 port already bound, some other program already listening
 */
public class FirstServerSocket {

	static public void main( String[] args ) {

		String usage = "Usage: " + FirstServerSocket.class.getName() +
			" port";

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
		
		try {
			ServerSocket ss = new ServerSocket( port );
			System.out.println( "Listening on : " + ss );
			// The accept call blocks us until a client program connects...
			Socket s = ss.accept();
			System.out.println( "Connected to : " + s );
			s.close();
			ss.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit(1);
		}
	}
}

// eof
