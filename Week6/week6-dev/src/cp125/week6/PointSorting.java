package cp125.week6;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Stuart Maclean
 *
 */
 
public class PointSorting {

	static public void main( String[] args ) {

		List<Point> ps = new ArrayList<Point>();
		ps.add( new Point( 3, 4 ) );
		ps.add( new Point( 5, 12 ) );
		ps.add( new Point( 0, 0 ) );
		ps.add( new Point( -10, 10 ) );

		System.out.println( "List as added " + ps );

		Collections.sort( ps );
		System.out.println( "Sorted by distance to origin " + ps );

		Collections.sort( ps, MINX );
		System.out.println( "Sorted by smallest x " + ps );

		Collections.sort( ps, MAXX );
		System.out.println( "Sorted by biggest x " + ps );

		/*
		  The Collections.sort operation sorts 'in place' so does
		  NOT make a copy.  So the final list order is given by the
		  most recent sort operation, in our case the sort by MAXX
		*/
		System.out.println( "Final contents " + ps );
	}


	// Other comparators, as anonymous classes...

	// This comparator considers only the x coordinate, uses <
	static final Comparator<Point> MINX = new Comparator<Point>() {
		public int compare( Point p1, Point p2 ) {
			if( p1.x < p2.x )
				return -1;
			if( p2.x < p1.x )
				return 1;
			return 0;
		}
	};

	// This comparator considers only the x coordinate, uses >
	static final Comparator<Point> MAXX = new Comparator<Point>() {
		public int compare( Point p1, Point p2 ) {
			if( p1.x > p2.x )
				return -1;
			if( p2.x > p1.x )
				return 1;
			return 0;
		}
	};

	// This comparator considers only the y coordinate, uses <
	static final Comparator<Point> MINY = new Comparator<Point>() {
		public int compare( Point p1, Point p2 ) {
			if( p1.y < p2.y )
				return -1;
			if( p2.y < p1.y )
				return 1;
			return 0;
		}
	};

	// This comparator considers only the y coordinate, uses >
	static final Comparator<Point> MAXY = new Comparator<Point>() {
		public int compare( Point p1, Point p2 ) {
			if( p1.y > p2.y )
				return -1;
			if( p2.y > p1.y )
				return 1;
			return 0;
		}
	};
}


// eof

	