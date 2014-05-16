package cp125.week1;

/**
 * @author Stuart Maclean
 *
 * A class with a single method to sum two passed parameters
 */

public class Adder {

	static public int add( int a, int b ) {
		int result = a;
		for( int i = 1; i <= b; i++ )
			result++;
		return result;
	}
}

// eof

		