package cp125.week5;

import java.util.Random;

/**
 * @author Stuart Maclean
 *
 * A Line class defined by its two Point endpoints.
 */

public class Line {

	public Line( Point from, Point to ) {
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return from + " - " + to;
	}

	/**
	 * Note the signature: equals( Object ) NOT equals( Line ) !!
	 */
	@Override
	public boolean equals( Object o ) {
		if( this == o )
			return true;
		if( o == null )
			return false;
		if( !this.getClass().equals( o.getClass() ) )
			return false;
		Line that = (Line)o;
		return this.from.equals( that.from ) &&
			this.to.equals( that.to );
	}

	@Override
	public int hashCode() {
		return from.hashCode() + 31 * to.hashCode();
	}

	public double length() {
		int dx = to.x - from.x;
		int dy = to.y - from.y;
		// Remember Pythagoras from school...
		return Math.sqrt( dx*dx + dy*dy );
	}
		
	Point from, to;
}

// eof

		