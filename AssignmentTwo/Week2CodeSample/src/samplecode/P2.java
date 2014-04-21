package cp125.week2;

/**
 * @author Stuart Maclean
 *
 * A simple Point class for modelling points in 2D space.  Includes
 * some typical operations.  Note the toString() operation, inherited
 * from java.lang.Object.  It enables us 'print' our points in some
 * meaningful way.
 */

public class P2 {

	public P2() {
		x = y = 0;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}
	
	/*
	  See how keyword (NOT identifier!) 'this' is valid when accessing
	  instance member variables.  It is NOT mandatory, but will be
	  needed to disambiguate any member variable whose name clashes
	  with a parameter name.
	*/
	public void set( int newX, int newY ) {
		this.x = newX;
		y = newY;
	}

	public void report() {
		System.out.println( "X " + x + ", Y " + y );
	}

	public void copyFrom( P2 other ) {
		set( other.x, other.y );
	}

	public void copyFromSecondWay( P2 other ) {
		this.x = other.x;
		this.y = other.y;
	}

	// remember Pythagoras from school...
	public double distance() {
		return Math.sqrt( x*x + y*y );
	}
	
	int x, y;
}

// eof
