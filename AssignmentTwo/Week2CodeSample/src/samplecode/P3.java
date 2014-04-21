package cp125.week2;

/**
 * @author Stuart Maclean
 *
 * A simple Point class for modelling points in 3D space.  Includes
 * some typical operations AND extends from the simpler P2.
 */

public class P3 extends P2 {

	public P3() {
		super();
		z = 0;
	}

	public void set( int newX, int newY, int newZ ) {
		// super not strictly necessary due to signature switch...
		super.set( newX, newY );
		z = newZ;
	}

	@Override
	public void report() {
		System.out.println( "X " + x + ", Y " + y + ", Z " + z );
	}

	public void copyFrom( P3 other ) {
		set( other.x, other.y, other.z );
	}

	@Override
	public double distance() {
		return  Math.sqrt( x*x + y*y + z*z);
	}

	// some arbitary 'P3-only' method, i.e. NOT defined in P2
	public boolean couldBe2D() {
		return z == 0;
	}
		
	int z;
}

// eof
