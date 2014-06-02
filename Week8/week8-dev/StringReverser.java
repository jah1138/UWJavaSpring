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
 * Listen on port 52108/tcp.  When a client connects, read a line over
 * the socket, tokenize and reverse and send result back.  Repeat.
 */
public class StringReverser {

	static public void main( String[] args ) {
		
		try {
			ServerSocket ss = new ServerSocket( 52108 );
			System.out.println( "Listening: " + ss );
			while( true ) {
				Socket s = ss.accept();
				System.out.println( "Connected: " + s );
				process( s );
				s.close();
			}
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit( 1 );
		}
	}

	static void process( Socket s ) throws IOException {
			
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
}

// eof
