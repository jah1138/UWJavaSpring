package samplecode;

/**
 * @author Stuart Maclean
 *
 * Showing a sequence of method calls in Java.  A 'stack frame' is allocated
 * for each call.  You cannot 'see' the stack in the code, but it is there!
 *
 * The stack frames viewed as a whole show what is 'left to do' in
 * your program, nce the current method returns.
 *
 * A static method call is of the form
 *
 * methodName( args );
 *
 * There is no '.' preceding the methodName, nor any identifier preceding that.
 *
 * An instance method call is of the form
 *
 * variable.methodName( args );
 *
 * An instance method call is supplied a reference to an object, sometimes
 * called the 'target' of the call.  The fields of the target object are
 * then likely accessed by the method.
 */

public class MethodCalls {

	static public void main( String[] unused ) {

		// static method call, NO 'variable.' preceding methodName
		isPI( 3.14 );

		// static method call
		otherFunc( "hello" );

		// static method call
		calcWrongLength( "world" );

		Object o = new Object();
		/*
		  Instance method call. o identifies WHICH object is available
		  to the Object.toString method
		*/
		o.toString();
	}

	static boolean isPI( double d ) {
		return d == 3.14;
	}

	static void otherFunc( String s ) {
		int len = calcWrongLength( s );
		isPI( len );
	}

	static int calcWrongLength( String s ) {
		return s.length() + 100;
	}
}

// eof

		