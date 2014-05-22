package cp125.week7;

/**
 * @author Stuart Maclean
 *
 * The complexity of a program with Object-Oriented features, namely
 * inheritance and polymorphism.  You can quite easily imagine what the
 * given output of the program would be, just by reading the code. And you
 * would expect that output to be the same, no matter how many times you
 * were to run the program.
 */
public class OOPNoThreads {

	abstract static class Shape {
		abstract public double area();
	}
	
	static class Circle extends Shape {
		public Circle( int r ) {
			this.r = r;
		}
		public double area() {
			return Math.PI*r*r;
		}
		int r;
	}
	
	static class Rectangle extends Shape {
		public Rectangle( int w, int h ) {
			this.w = w;this.h = h;
		}
		public double area() {
			return w*h;
		}
		int w, h;
	}
	
	static public void main( String[] args ) {
		Shape s = new Circle( 5 );
		System.out.println( s.area() );
		s = new Rectangle( 6, 6 );
		System.out.println( s.area() );
	}
}

// eof
