package cp125.week4.devbundle;

/**
 * @author Stuart Maclean
 *
 * Convert a decimal number, given in args[0], to binary, by printing
 * out which bits (counting right-to-left from 0) would be set in the
 * result
 */

public class DecimalToBinary {

	static public void main( String[] args ) {

		long l = Long.parseLong( args[0] );

		long d = l;
		while( d > 0 ) {
			int p = 0;
			while( 1L << p <= d )
				p++;
			p--;
			System.out.print( p + " " );
			d -= (1L << p);
		}
		System.out.println();
	}
}

// eof
