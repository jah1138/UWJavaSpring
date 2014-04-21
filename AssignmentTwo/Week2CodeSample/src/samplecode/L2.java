package cp125.week2;

/**
 * @author Stuart Maclean
 *
 * A simple Line class.  A Line joins two P2 (Point) objects.  Really
 * more of a line segment, but at least highlights how objects can
 * have members which are reference types, i.e. can point to other
 * objects on the heap.
 */

public class L2 {

	public L2() {
		from = to = null;
	}

	public void set( P2 from, P2 to ) {
		this.from = from;
		this.to = to;
	}

	public void report() {
		System.out.println( "From " + from + " to " + to );
		System.out.println( "Length " + String.format( "%.2f", length() ) +
							", theta " + String.format( "%.2f", theta() ) );
	}

	/**
	 * @return length of this line segment
	 */
	public double length() {
		int dy = to.y - from.y;
		int dx = to.x - from.x;
		return Math.sqrt( dy*dy + dx*dx );
	}

	/**
	 * @return orientation of this line, in degrees
	 */
	public double theta() {
		int dy = to.y - from.y;
		int dx = to.x - from.x;
		// atan2 returns radians, converts to degrees
		return 180/Math.PI * Math.atan2( dy, dx );
	}
		
	/*
	  Our two members (fields) are object references, and can reference
	  any P2 object instance or any instance of class derived from P2 (e.g. P3)
	*/
	P2 from, to;
}

// eof
