package cp125.week4;

/**
 * @author Stuart Maclean
 *
 * Write out the first one million natural numbers, i.e. 1
 * .. 1,000,000, to a file using the combination of DataOutputStream,
 * BufferedOutputStream and FileOutputStream.
 *
 * Compare the performance of this program with that of
 * WriteMillionNaturals in this code bundle.
 */

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferWriteMillionNaturals {

	static public void main( String[] args ) {

		try {
			FileOutputStream sink = new FileOutputStream( "naturals.buffered" );
			int oneMegaByte = 1 << 20;
			BufferedOutputStream filter1 = new BufferedOutputStream
				( sink, oneMegaByte );
			DataOutputStream filter2 = new DataOutputStream( filter1 );
			int limit = 1000000;
			for( int i = 1; i <= limit; i++ )
				filter2.writeInt( i );
			filter2.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
	}
}

// eof
