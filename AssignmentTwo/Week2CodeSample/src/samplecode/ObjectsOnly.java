package samplecode;

/**
 * @author Stuart Maclean
 *
 * Showing how object allocation is independent of any variable
 * assignment, and of 'names' in general.  This is a valid program, it
 * compiles and runs.  Of course it is not very USEFUL...
 */

public class ObjectsOnly {

	static public void main( String[] unused ) {
		new Object();

		new java.util.ArrayList<String>();

		new ObjectsOnly();

		new java.util.Stack<Exception>();

	}
}

// eof

		