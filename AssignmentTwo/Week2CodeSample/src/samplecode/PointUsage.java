package cp125.week2;

/**
 * @author Stuart Maclean
 *
 * A program making use of local point classes P2 and P3.  We show how
 * polymorphism works and note its limitations.
 */

public class PointUsage {

	static public void main( String[] unused ) {
		System.out.println( "p2Operations" );
		p2Operations();

		System.out.println( "p3Operations" );
		p3Operations();
		
		System.out.println( "showPolymorphism" );
		showPolymorphism();

		System.out.println( "mixedOperations" );
		mixedOperations();
	}

	static public void p2Operations() {
		P2 a = new P2();
		a.set( 3, 4 );
		a.report();
		System.out.println( "Distance " + a.distance() );
	}

	static public void p3Operations() {
		P3 a = new P3();
		a.set( 3, 4, 12 );
		a.report();
		System.out.println( "Distance " + a.distance() );
	}

	static public void showPolymorphism() {
		P2 a = new P2();
		a.set( 3, 4 );
		a.report();

		P3 b = new P3();
		b.set( 3, 4, 12 );

		/*
		  What polymorphism buys us. 'a' can refer to the same object
		  as 'b'.  Methods calls using 'a' can then run P3 methods!
		*/
		a = b;
		a.report();

		/*
		  BUT BUT BUT

		  'a' was declared as type P2. The type of 'a' is then ALWAYS
		  P2, not matter what object it currently references (points
		  to!).  These next lines wouldn't compile.  The fact that 'a'
		  might at some point in its life refer to a P3 is irrelevant
		*/

		// P2 has no z member variable
		// a.z = 5;

		// P2 has no method couldBe2D
		// boolean x = a.couldBe2D();
		  
		
	}

	/*
	   See how aliasing (object references refering to the SAME object)
	   affect what looks like the same operation 'a.report'
	*/
	static public void mixedOperations() {
		P2 a = new P2();
		P3 b = new P3();
		P2 c = a;
		
		a.set( 3, 4 );
		a.report();
		System.out.println( "Distance A " + a.distance() );

		b.set( 3, 4, 12 );
		b.report();

		c.set( 6, 8 );
		a.report();
		System.out.println( "Distance A " + a.distance() );
	}
}

// eof

		