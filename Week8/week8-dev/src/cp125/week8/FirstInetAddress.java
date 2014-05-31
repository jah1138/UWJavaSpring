package cp125.week8;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author Stuart Maclean
 *
 * Use of java.net.InetAddress.  Supply either a dotDecimal or Domain
 * Name System (DNS) name in args[0], and this program resolves the
 * name to an InetAddress.  It does so by asking the underlying
 * operating system for the answer.
 */
public class FirstInetAddress {

	static public void main( String[] args ) {

		String usage = "Usage: " + FirstInetAddress.class.getName() +
			" host";

		if( args.length < 1 ) {
			System.err.println( usage );
			System.exit(1);
		}

		String host = args[0];

		try {
			InetAddress ia = InetAddress.getByName( host );
			System.out.println( ia );

			// To inspect the underlying 32-bit int value, do this...
			byte[] ba = ia.getAddress();
			long l = fromBytes( ba );
			System.out.println( l );
			
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}			
	}

	/*
	  Recall the big-endian nature of Network Addresses.  The bytes
	  located lowest in memory (i.e. at smallest array indices)
	  contribute the most significant bits to the result
	*/
	static long fromBytes( byte[] bs ) {
		return ((bs[0] & 0xffL) << 24) |
			((bs[1] & 0xffL) << 16) |
			((bs[2] & 0xffL) << 8) |
			((bs[3] & 0xffL) << 0);
	}
}

// eof
