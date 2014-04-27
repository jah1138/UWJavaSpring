package cp125.week3;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * @author Stuart Maclean
 *
 * Examine the 'character sets' supported by the local Java runtime.
 * Also known as 'character encodings' since charsets map (encode)
 * 16-bit characters into sequences of 8-bit bytes.
 */
 
public class PlatformCharsets {

	static public void main( String[] args ) {

		// all the charsets available to a program on this platform...
		SortedMap<String,Charset> cs = Charset.availableCharsets();
		for( String name : cs.keySet() ) {
			System.out.println( name );
		}

		// the local default charset
		Charset defaultCS = Charset.defaultCharset();
		System.out.println( "Default Charset is : " + defaultCS.name() );

	}
}

// eof

		
		
