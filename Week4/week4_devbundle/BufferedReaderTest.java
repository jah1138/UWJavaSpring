package cp125.week4.devbundle;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.LineNumberReader;
import java.io.IOException;

/**
 * @author Stuart Maclean
 *

 */

public class BufferedReaderTest {

	static public void main( String[] args ) throws IOException {

		int bufSize = 0;
		if( args.length > 0 )
			bufSize = Integer.parseInt( args[0] );
		
		File f = new File( "seq.10000000" );

		if( !f.isFile() )
			System.exit(0);

		FileReader fr = new FileReader( f );
		LineNumberReader lnr;
		if( bufSize > 0 )
			lnr = new LineNumberReader( fr, bufSize );
		else
			lnr = new LineNumberReader( fr );
		
		while( true ) {
			String line = lnr.readLine();
			if( line == null )
				break;
		}
	}
}

// eof

		