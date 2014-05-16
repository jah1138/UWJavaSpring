package cp125.week5;

/**
 * @author Stuart Maclean
 *
 * Studying the ideas of objects being 'the same' or 'equal'.  Data
 * structures in the Java Collections Framework (i.e those in package
 * java.util) work on the concept of object equality rather than
 * variable equality/sameness.  We use local classes Point and Line to
 * illustrate.
 *
 */

public class SameAndEqual {

	static public void main( String[] args ) {

		Point p1 = new Point( 0, 0 );
		Point p2 = new Point( 0, 0 );

		/*
		  Asking if two variables are 'equal' means asking
		  if they refer to (point to!) the same object on the heap.
		  This test uses the '==' operator.  This test does NOT inspect
		  the contents of the referenced object(s)!
		*/
		System.out.println( p1 == p2 ); // true or false?  Run and see

		System.out.println( p1 == p1 ); // true or false?  Run and see
	}
	   
}

// eof

		