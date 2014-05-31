package cp125.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Stuart Maclean
 *
 * A multi-threaded version of the StringReverser service.  Will not
 * be 'hung' up by any client.  Could still be the target of
 * Denial-Of-Service (DoS) attacks though, since we are consuming
 * local resources (sockets) at the request of remote, unknown,
 * clients! A real server would likely timeout open connections over
 * which no data was flowing.
 *
 */
public class StringReverserMT {

	static public void main( String[] args ) {
		
		try {
			ServerSocket ss = new ServerSocket( 52108 );
			System.out.println( "Listening: " + ss );
			while( true ) {
				Socket s = ss.accept();
				System.out.println( "Connected: " + s );
				/*
				  Start the new thread as early as we can, allowing
				  the server to go back to the next accept call. So we
				  handle the new socket off to a worker, which does
				  everything else regarding the new connection.
				*/
				Worker w = new Worker( s );
				new Thread( w ).start();
			}
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit( 1 );
		}
	}

	static class Worker implements Runnable {
		Worker( Socket s ) {
			this.s = s;
		}

		@Override
		public void run() {
			try {
				process( s );
				s.close();
			} catch( IOException ioe ) {
				System.err.println( ioe );
			}
		}

		/*
		  Trying to maintain the same structure as StringReverser. What
		  was 'static void process( Socket )' becomes 'void process(
		  Socket )'.  The code inside the two functions is the same.
		*/
		void process( Socket s ) throws IOException {
			// read input, a line of text, so need a reader
			InputStream source = s.getInputStream();
			BufferedReader br = new BufferedReader
				( new InputStreamReader( source ) );
			String line = br.readLine();
			
			// process the line...
			String[] toks = line.split( "\\s" );
			List<String> l = Arrays.asList( toks );
			Collections.reverse( l );
			
			// output back to client.  text, so need a writer
			OutputStream sink = s.getOutputStream();
			PrintWriter pw = new PrintWriter( sink );
			for( String el : l ) {
				pw.print( el + " " );
			}
			pw.println();
			// without this the data does not seem to go out on the wire.
			pw.flush();
			
			source.close();
			sink.close();
			// we don't close what we didn't build!
		}

		private final Socket s;
	}
}

// eof
