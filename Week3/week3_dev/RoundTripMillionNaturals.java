package cp125.week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Stuart Maclean
 *
 * Round trip test.  Create an array containing the first million
 * natural numbers.  Write it out to a file using File/PrintWriter,
 * one number per line.  Then close the file, re-open it for read and
 * read all the numbers back in to a second array.  Verify the two
 * arrays are equal using java.util.Arrays.equals
 */
 
public class RoundTripMillionNaturals {

	static public void main( String[] args ) {

		int[] a1 = new int[1000000];
		int[] a2 = new int[1000000];

		// Populate a1: use the 'length' operator rather than constant
		for( int i = 0; i < a1.length; i++ )
			a1[i] = i+1;

		File f = new File( "millionNaturals.txt" );
		try {
			// write out a1
			PrintWriter pw = new PrintWriter( f );
			for( int i = 0; i < a1.length; i++ ) {
				pw.println( a1[i] );
			}
			pw.close();

			// read data back into a2
			FileReader fr = new FileReader( f );
			BufferedReader br = new BufferedReader( fr );
			String line;
			int i = 0;
			while( (line = br.readLine()) != null ) {
				a2[i] = Integer.parseInt( line );
				i++;
			}
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}

		boolean b = java.util.Arrays.equals( a1, a2 );
		System.out.println( "The arrays are equal: " + b );
	}
}

// eof

		
		
