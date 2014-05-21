package cp125.week6;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Stuart Maclean.  Generate a list of N Points with random
 * values, and then search for one, the origin point (0,0). The exercise
 * is fill in the implementation of 'searchPointList'
 */
 
public class PointSearchExercise {

	static public void main( String[] args ) {

		int N = 100;
		if( args.length > 0 ) {
			try {
				N = Integer.parseInt( args[0] );
			} catch( NumberFormatException nfe ) {
			}
		}
		
	
		List<Point> ps = new ArrayList<Point>();

		for( int i = 1; i <= N; i++ ) {
			Point p = Point.random();
			ps.add( p );
		}

		Point theOrigin = new Point( 0, 0 );
		boolean containsOrigin = searchPointList( ps, theOrigin );
		System.out.println( "Found the origin: " + containsOrigin );

	}

	static boolean searchPointList( List<Point> ps, Point needle ) {
		//		return linearSearch( ps, needle );
		return binarySearch( ps, needle );
	}




	
	static boolean linearSearch( List<Point> ps, Point needle ) {
		for( Point p : ps ) {
			if( needle.equals( p ) )
				return true;
		}
		return false;
	}





	
	static boolean binarySearch( List<Point> ps, Point needle ) {
		int i = Collections.binarySearch( ps, needle );
		return i >= 0;
	}
}

// eof

	