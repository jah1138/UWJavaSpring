package cp125.week4;

/**
 * @author Stuart Maclean
 *
 * Write out the first one million natural numbers, i.e. 1 .. 1,000,000,
 * to a file using the combination of DataOutputStream and FileOutputStream.
 * So we are producing a 'binary file', NOT one filled with readable numbers.
 *
 * Make no use of any BufferedOutputStream.
 *
 * Contrast the performance of this program with
 * BufferWriteMillionNaturals, in this code bundle.
 */

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteMillionNaturals {

	static public void main( String[] args ) {

		try {
			FileOutputStream sink = new FileOutputStream( "naturals" );
			DataOutputStream filter = new DataOutputStream( sink );
			int limit = 1000000;
			for( int i = 1; i <= limit; i++ )
				filter.writeInt( i );
			filter.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
}

// eof
