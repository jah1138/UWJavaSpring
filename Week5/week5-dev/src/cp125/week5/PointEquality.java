package cp125.week5;

import java.util.List;
import java.util.ArrayList;

/**
 * @author Stuart Maclean
 *
 */
 
public class PointEquality {

	static public void main( String[] args ) {

		List<Point> ps = new ArrayList<Point>();
		Point p1 = new Point( 1, 1 );
		Point p2 = new Point( 2, 2 );
		ps.add( p1 );
		ps.add( p2 );
		
		Point p3 = new Point( 1, 1 );
		boolean b1 = ps.contains( p3 ); // expected ?
		
		boolean b2 = ps.remove( p3 );   // expected ?
		
		boolean b3 = ps.contains( p3 ); // expected ?

		System.out.println( b1 + " " + b2 + " "  + b3 );
							
	}

	static class Point {
		Point( int x, int y ) {
			this.x = x;
			this.y = y;
		}

		/**
		  This is the INCORRECT signature for equals.
		  This method never gets called.  Instead, Object.equals( Object )
		  is called (assuming Point's superclass is Object).
		  And Object.equals( Object ) only provides 'sameness'.

		  To make this class work 'as expected' with respect to Point
		  equality, uncomment out Point.equals( Object ) above and
		  delete/comment this function.
		*/
		/*
		  public boolean equals( Point that ) {
			return (this.x == that.x && this.y == that.y);
		}
		*/

		/**
		   This is CORRECT signature for equals. To make this class
		   work 'as expected', uncomment this...
		*/

		public boolean equals( Object o ) {
			// no guarantee that o is a Point at all...
			if( !( o instanceof Point ) )
				return false;
			Point that = (Point)o;
			return (this.x == that.x && this.y == that.y);
		}


		/**
		   Further, even with a correct equals method, Point equality
		   is only sure to work 'as expected' if the hashCode function
		   returns the same value for Points p1 and p2 desired to be equal.

		   The general contract in the Java Collections Framework is that:

		   A.equals( B ) implies that A.hashCode() == B.hashCode();

		   The converse does not have to hold. A valid hashCode is 0
		   for all objects.  Though valid, this is a poor choice.

		   Further:

		   A.hashCode() != B.hashCode() implies that A.equals( B ) is false.

		   So if hashCodes of two objects A and B are different, the
		   Java collections API is at liberty to deem them 'unequal'
		   (even if A.equals( B ) were to return true!)
		*/
		/*
		public int hashCode() {
			return x + y;
		}
		*/
		final int x, y;
	}
}

// eof

	