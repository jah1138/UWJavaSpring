package cp125.week4.devbundle;

/**
 * @author Stuart Maclean
 *
 * Write out the first one million natural numbers, i.e. 1 .. 1,000,000,
 * to a file using the combination of PrintWriter, FileWriter.
 * So we are producing a 'text file', one filled with readable numbers.
 *
 * Contrast this with WriteMillionNaturals, which produces a binary file
 * from the same data set (1..1000000).  Compare the file sizes.
 */

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteMillionNaturalsText {

	static public void main( String[] args ) {

		try {
			FileWriter sink = new FileWriter( "naturals.txt" );
			PrintWriter pw = new PrintWriter( sink );
			int limit = 1000000;

			for( int i = 1; i <= limit; i++ )
				pw.println( i );

			// why not this, would save space?
			/*
			  for( int i = 1; i <= limit; i++ )
				pw.print( i );
			*/
			
			pw.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
}

// eof
