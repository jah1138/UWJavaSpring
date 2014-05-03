package cp125.week4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Stuart Maclean
 *
 * How to manipulate program configuration data using class
 * <code>java.util.Properties</code>.  We can load and store such an
 * object.
 *
 * Note how the <code>load</code> method can handle comments but that
 * they are not transferred into the in-memory
 * <code>java.util.Properties</code> object.  So a load,store sequence
 * cannot preserve comments.  Note however that the <code>store</code>
 * operation supplies comments of its own.
 *
 * Run this program twice.  First without the file ${user.home}/cp125.prp and
 * second with that file, populated with some test key/value pairs
 */
 
public class PropertyFiles {

	static public void main( String[] args ) {

		Properties p = new Properties();
		try	{
			String userHome = System.getProperty( "user.home" );
			File dir = new File( userHome );
			File config = new File( dir, "cp125.prp" );
			FileReader fr = new FileReader( config );
			p.load( fr );
			fr.close();
		} catch( FileNotFoundException noProblem ) {
		} catch( IOException ioe ) {
			System.err.println( "Load Error" + ioe );
		}
		System.out.println( "Properties: " + p );

		try {
			FileWriter sink = new FileWriter( "scratchFile.prp" );
			p.store( sink, "Java is cool" );
			sink.close();
		} catch( IOException ioe ) {
			System.err.println( "Store Error" + ioe );
		}
	}
}

// eof

		
		
