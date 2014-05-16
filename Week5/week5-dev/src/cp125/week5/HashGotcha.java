package cp125.week5;

import java.util.Set;
import java.util.HashSet;

/**
 * @author Stuart Maclean
 *
 * Highlights the fatal gotcha whereby the computed hashCode of an object
 * changes AFTER it has be added to a collection whose implementation relies
 * on hashing, in our case a HashSet.  The sequence of operations on the Set is
 *
 * add( p );
 * contains( p );
 * contains( p );
 *
 * which would appear to imply that the two 'contains' calls must return the
 * same value, and that value be TRUE.  However, with an update operation on
 * a set member such that that member's hashCode is changed, the second
 * call to 'contains' does NOT locate the member (even though it IS a member)
 * and thus returns FALSE.
 *
 * We use the local Point class as the set element type.
 */
 
public class HashGotcha {

	static public void main( String[] args ) {

		Set<Point> ps = new HashSet<Point>();

		// create a point P and inspect its state
		Point p = new Point( 1, 1 );
		System.out.println( "P is " + p );
		
		// add it to a set
		ps.add( p );

		// if p was just added, surely 'contains' must return TRUE?
		boolean b1 = ps.contains( p );
		System.out.println( "P a member? " + b1 );

		/*
		  We know update p such that its hashCode will be different
		  compared when the object was created.  This could also
		  be via a 'setter' method like p.setX( newX ) but the net
		  effect is the same.  Inspect the state after the update
		*/
		p.x++;
		System.out.println( "P is " + p );
		
		/*
		  If no removal of p has occured, surely 'contains' must
		  return TRUE?  It was added, NOT removed, so it must still be
		  a member?
		*/
		boolean b2 = ps.contains( p );
		System.out.println( "P still a member? " + b2 );

		/*
		  If contains fails, so too will remove...
		*/
		boolean b3 = ps.remove( p );
		System.out.println( "P removed? " + b3 );
	}

}

// eof
