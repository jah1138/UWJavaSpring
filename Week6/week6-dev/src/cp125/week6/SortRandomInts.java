package cp125.week6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Stuart Maclean
 *
 * Create list of int, populated with N ints from a random stream
 * (java.util.Random, which may be seeded or not).  Use
 * Collections.sort to sort the list, then print it out.
 */
 
public class SortRandomInts {

	static public void main( String[] args ) {

		List<Integer> is = new ArrayList<Integer>();
		Random r = null;

		if( true ) {
			r = new Random();
		} else {
			// for a repeatable stream, provide a known 'seed'
			int seed = 21;
			r = new Random( seed );
		}

		int N = 1000;
		if( args.length > 0 ) {
			try {
				N = Integer.parseInt( args[0] );
			} catch( NumberFormatException nfe ) {
			}
		}

		for( int i = 1; i <= N; i++ ) {
			int next = r.nextInt( 100 );
			is.add( next );
		}

		// Never need to sort ourselves, use Collections.sort
		Collections.sort( is );

		System.out.println( is );
	}
}

// eof
