package cp125.week6;

import java.util.Set;
import java.util.HashSet;

/**
 * @author Stuart Maclean
 *
 * Highlights the fatal gotcha whereby the computed hashCode of an object
 * changes AFTER it has be added to a collection whose implementation relies
 * on hashing, in our case a HashSet.  The sequence of operations on the Set is
 *
 * Collection.add( p );
 * Collection.contains( p );
 * mutate p
 * Collection.contains( p );
 *
 * which would appear to imply that the two 'contains' calls must return the
 * same value, and that value be TRUE.  However, with an update operation on
 * a set member such that that member's hashCode is changed, the second
 * call to 'contains' does NOT locate the member (even though it IS a member)
 * and thus returns FALSE.
 */
 
public class HashGotcha {

	static public void main( String[] args ) {
		Set<Point> ps = new HashSet<Point>();
		Point p1 = new Point( 1, 1 );
		ps.add( p1 );
		System.out.println( "P1 added, set size is " + ps.size() );

		// if p1 was just added, surely 'contains' must return TRUE?
		boolean b1 = ps.contains( p1 );
		System.out.println( "P1 in? " + b1 + ". Set size " + ps.size() );

		p1.x++;

		// if no removal of p1 has occured, surely 'contains' must return TRUE?
		boolean b2 = ps.contains( p1 );
		System.out.println( "P1 in? " + b2 + ". Set size " + ps.size() );
	}
}

// eof
