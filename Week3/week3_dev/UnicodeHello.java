package cp125.week3;

/**
 * @author Stuart Maclean
 *
 * Define the string "Hello" using the Unicode syntax for each character.
 * Then print it out to convince us that
 *
 * "\u0068\u0065\u006c\u006c\u006f";
 *
 * really is the same string as
 *
 * Hello".
 */
public class UnicodeHello {

	static public void main( String[] args ) {
		String hello = "\u0068\u0065\u006c\u006c\u006f";

		System.out.println( hello );
	}
}

// eof
