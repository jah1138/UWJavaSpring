import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * Highlight how to actively connect to a remote host and port,
 * creating a so-called 'socket connection'.  Data can then flow in
 * both directions over the socket.  We connect to google.com's web
 * site (port80).
 *
 * The data flowing from port 80 (normally called the 'traffic', in
 * this 'we traffic'), is text-based. The application-level protocol
 * is HyperText Transport Protocol (http) and the content is any one of

 * Html pages
 * Javascript code
 * png,jpeg files
 * etc
 *
 * Due to this text-based traffic, in Java we use Readers and Writers.
 * To write etxt 'to the socket' we use a PrintWriter.  To read 'from
 * the socket', we use a BufferedReader.
 */
public class HiGoogle {

	static public void main( String[] args ) {
		
		try {
			Socket s = new Socket( "www.google.com", 80 );
			System.out.println( "Connected to : " + s );
			InputStream source = s.getInputStream();
			OutputStream sink = s.getOutputStream();
			PrintWriter pw = new PrintWriter( sink );
			BufferedReader br = new BufferedReader
				( new InputStreamReader( source ) );

			// We have text I/O capabilities with the peer
			System.out.println( "Writing..." );
			// http says a 'request' ends with a blank line, i.e. \r\n
			pw.print( "GET / HTTP/1.0\r\n\r\n" );
			pw.flush();

			System.out.println( "Reading..." );
			String line = br.readLine();
			System.out.println( line );
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit(1);
		}
	}
}

// eof
