package cp125.week3;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * @author Stuart Maclean
 *
 * The program that writes the 'haystack of numbers' file used in
 * class as an exercise in finding the needle in the haystack.  The
 * 'haystack exercise' is to find which line in a text file has only
 * one token when split using ',' as delimiter.  All other lines have
 * two tokens.  The contents of the tokens are not important.
 *
 * The exercise would involve use of LineNumberReader, FileReader,
 * String.split, String.length
 */

public class HaystackWriter {

	static public void main( String[] unused ) {

		/*
		  This is the line that is different from all others in that it
		  has one token only, no ',' delimiter at all
		*/
		int needle = 1 << 20;

		try {
			FileWriter fw = new FileWriter( "numbers.txt" );
			PrintWriter pw = new PrintWriter( fw );
			for( int i = 1; i <= (1 << 20); i++ ) {
				if( i == needle )
					pw.println( "" + i );
				else
					pw.println( "" + i + "," + (2*i) );
			}
			pw.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
		
}

// eof

		