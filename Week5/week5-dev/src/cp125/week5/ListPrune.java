package cp125.week5;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stuart Maclean
 *
 * An exercise: write a method which removes duplicates from a list of strings.
 */


public class ListPrune {

	static public void main( String[] args ) {

		List<String> ss = new ArrayList<String>();
		ss.add( "the" );
		ss.add( "cow" );
		ss.add( "flew" );
		ss.add( "over" );
		ss.add( "the" );
		ss.add( "moon" );
		ss.add( "and" );
		ss.add( "flew" );
		ss.add( "over" );
		ss.add( "another" );
		ss.add( "cow" );
		ss.add( "haha" );

		System.out.println( ss );
		List<String> pruned = prune( ss );
		System.out.println( pruned );
	}

	/**
	 * @return a list with no duplicate strings, based on the contents of ss
	 */
	static List<String> prune( List<String> ss ) {
		// FIXME!! 
		return null;
	}
}

// eof

