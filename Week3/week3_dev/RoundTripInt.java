package cp125.week3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Stuart Maclean
 *
 * Round trip test.  Write out a single int to file, using
 * PrintWriter.println (which calls upon String.valueOf(int) to
 * convert the int to the characters required by a PrintWriter.

 * Then use FileReader to read the characters back in, and parse to
 * int.  Verify that the ints written and read have the same value.
 */
 
public class RoundTripInt {

	static public void main( String[] args ) {

		int i1 = 21;

		File f = new File( "singleInt.txt" );
		try {
			// write out i1
			PrintWriter pw = new PrintWriter( f );
			pw.println( i1 );
			pw.close();

			// read data back into i2
			FileReader fr = new FileReader( f );
			BufferedReader br = new BufferedReader( fr );
			String line = br.readLine();
			int i2 = Integer.parseInt( line );

			// compare the two to verify our roundtrips works (or not!)
			boolean b = i1 == i2;
			System.out.println( "Roundtrip int result: "  + b );
		} catch( Exception e ) {
			System.err.println( e );
		}
	}
}

// eof

		
		
