import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stuart Maclean
 *
 * Convert a 'dot decimal' representation of an IPv4 address to the
 * underlying 32-bit value.  Since these are unsigned, we must use a
 * Java long. We cannot fit 32 bits of magnitude in a Java in
 * (alternatively, we could STORE in a int, but would have to widen to
 * long to print the value).
 *
 * The dot decimal is supplied as args[0].  The raw equivalent is printed to
 * stdout.
 */
public class DotDecimal {

	static public void main( String[] args ) {

		String usage = "Usage: " + DotDecimal.class.getName() + " A.B.C.D";

		if( args.length < 1 ) {
			System.err.println( usage );
			System.exit(1);
		}
		String input = args[0];

		// Use a regular expression to describe the allowed input format...
		Pattern p = Pattern.compile
			( "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})" );
		Matcher m = p.matcher( input );
		if( !m.matches() ) {
			System.err.println( p.pattern() );
			System.err.println( usage );
			System.exit(1);
		}

		/*
		  Dot decimal values are naturally big-endian: the values to
		  the left contribute the most significant parts of the result
		*/
		int b3 = Integer.parseInt( m.group(1) );
		int b2 = Integer.parseInt( m.group(2) );
		int b1 = Integer.parseInt( m.group(3) );
		int b0 = Integer.parseInt( m.group(4) );
		
		long raw = toRaw( b3, b2, b1, b0 );
		System.out.println( raw );

		String roundTrip = toDotDecimal( raw );
		if( !roundTrip.equals( input ) ) {
			System.err.println( "Bad roundTrip: " + roundTrip );
		}
	}

	static long toRaw( int b3, int b2, int b1, int b0 ) {
		return ((b3 & 0xffL) << 24) |
			((b2 & 0xffL) << 16) |
			((b1 & 0xffL) << 8) |
			((b0 & 0xffL) << 0);
	}

	static String toDotDecimal( long raw ) {
		int b3 = (int)(raw >> 24) & 0xff;
		int b2 = (int)(raw >> 16) & 0xff;
		int b1 = (int)(raw >> 8) & 0xff;
		int b0 = (int)(raw >> 0) & 0xff;
		return "" + b3 + "." + b2 + "." + b1 + "." + b0;
	}
}

// eof