package cp125.week6;

import java.util.Random;

/**
 * @author Stuart Maclean
 *
 * Copy of cp125.week5.Point, updated to implement
 * java.lang.Comparable, which enables us to sort Points.  Comparable
 * is a means to say 'one object comes before or after another
 * object'.  What 'before' and 'after' actually mean for any given
 * class is up to the class designer.
 * 
 */

public class Point implements Comparable<Point> {

	public Point( int x, int y ) {
		this.x = x;
		this.y = y;
	}

	/**
	   The way compareTo works is that if this comes BEFORE that, we
	   return a negative value.  If this comes AFTER that, we return
	   a positive value, and if the two are deemed equal, we return 0.

	   We are choosing (arbitrarily) distance from the origin (0,0 )as
	   our notion of comparable.  This could be anything else we thought
	   as/more appropriate.
	*/
	@Override
	public int compareTo( Point that ) {
		
		double distanceThis = Math.sqrt( this.x*this.x + this.y*this.y );
		double distanceThat = Math.sqrt( that.x*that.x + 
										 that.y*that.y );
		if( distanceThis == distanceThat )
			return 0;
		return distanceThis < distanceThat ? -1 : 1;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

	/**
	 * Note the signature: equals( Object ) and NOT equals( Point ) !!
	 */
	@Override
	public boolean equals( Object o ) {
		if( this == o )
			return true;
		if( o == null )
			return false;
		// no guarantee that o is a Point at all...
		if( !this.getClass().equals( o.getClass() ) )
			return false;
		Point that = (Point)o;
		// finally, a field by field comparison defines equality
		return this.x == that.x && this.y == that.y;
	}

	/**
	   This is the INCORRECT signature for Point.equals.
	   This method never gets called.  Instead, Object.equals( Object )
	   is called (assuming Point's superclass is Object).
	   And Object.equals( Object ) only provides 'sameness' of object
	   reference, and does not compare object state in any way.

	   To make this class work 'as expected' with respect to Point
	   equality, use Point.equals( Object ) above and
	   delete/comment this function.
	*/
	/*
	  public boolean equals( Point that ) {
	  return (this.x == that.x && this.y == that.y);
	  }
	*/

	/**
	   Further, even with a correct equals method, Point equality
	   is only sure to work 'as expected' if the hashCode function
	   returns the same value for Points p1 and p2 desired to be equal.

	   The general contract in the Java Collections Framework is that:
	   
	   A.equals( B ) implies that A.hashCode() == B.hashCode();
	   
	   The converse does not have to hold. A valid hashCode is 0 for
	   all objects.  Though valid, this is a poor choice (since a
	   fixed hash ode like this 0 would produce collisions for all
	   objects and reduce a hash table to a linear list..

	   Further:
	   
	   A.hashCode() != B.hashCode() implies that A.equals( B ) is false.
	   
	   So if hashCode() result of two objects A and B are different,
	   the Java collections API is at liberty to deem them 'unequal',
	   even if A.equals( B ) were to return true!!
	*/


	/*
	  Eclipse,IDEA normally provide this method, and likely do better
	  than this, which would produce a hash collision for points (2,2) and
	  (1,3)
	*/
	@Override
	public int hashCode() {
		return x + y;
	}
				
	static public Point random() {
		return new Point( nextRand(), nextRand() );
	}

	
	/**
	 * @return a random int between 1 and 100
	 */
	static private int nextRand() {
		return 1 + R.nextInt(100);
	}

	int x, y;
	
	static final Random R = new Random(0);
}

// eof

		