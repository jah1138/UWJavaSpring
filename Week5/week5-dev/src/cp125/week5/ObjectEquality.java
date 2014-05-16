package cp125.week5;

/**
 * @author Stuart Maclean
 *
 * Inspecting the results of equality comparisons among instances of
 * java.lang.Object.  Also looking at values for Object.hashCode().
 */


public class ObjectEquality {

	static public void main( String[] args ) {

		Object o1 = new Object();

		Object o2 = new Object();

		// first, inspect the results of the equality operator '=='
		System.out.println( "o1 == o1: " + (o1 == o1) );

		System.out.println( "o1 == o2: " + (o1 == o2) );

		// next, inspect the results of Object.equals
		System.out.println( "o1.equals(o1): " + o1.equals(o1) );

		System.out.println( "o1.equals(o2): " + o1.equals(o2) );

		/*
		  Given the previous 4 results, can say that for class Object,
		  '==' operator and 'equals' method equivalent.
		*/

		/*
		  next look at hashCode, used in hashing data structures like
		  HashSet, HashMap.  Running this will show that any two
		  objects return different results for hashCode()
		*/
		System.out.println( "o1.hashCode(): " + o1.hashCode() );
		System.out.println( "o2.hashCode(): " + o2.hashCode() );
	}
}

// eof

		