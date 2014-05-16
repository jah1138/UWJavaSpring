package cp125.week4.devbundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Stuart Maclean
 *
 * Start with a simple string of one character: Unicode 03C0, which is
 * small greek letter pi (as in the constant 3.14159..).  This character
 * has no ASCII 8-bit representation.
 *
 * First, write this string out to a file using FileWriter.
 *
 * Then, for the standard character sets (character encodings) which
 * are required to be available on your JVM runtime, write the string
 * out using each char set.  Print the length of the file.  Good idea
 * to inspect the file contents (xxd or od good on Unix)
 *
 * Finally, try to read the file content back in to a second string, which
 * you would like to then also have the value 'pi'.  Sometimes, this fails.
 *
 * Another way to think about this is to imagine you are given a data
 * file which is supposedly text.  Yet you read it in using FileReader
 * (and thus get your platform's default charset used for the bytes to
 * chars conversion) and you get garbage.  Then it is likely that the
 * file was produced using some other character encoding, and the code
 * below shows how you successful read it in.
 */
 
public class CharacterEncodings {

	static public void main( String[] args ) {
		
		// some of the more popular character sets.  Required to be supported
		String[] popular = { "US-ASCII",   // 7-bit ASCII
							 "ISO-8859-1", // 8-bit ISO-LATIN-1
							 "UTF-8", 
							 "UTF-16LE",
							 "UTF-16BE",
							 "UTF-16" };   // will have ByteOrderMark (BOM)

		// Unicode literals are of the form \\uXXXX for 4 hex digits X 
		String pi = "\u03C0";

		/*
		  write out pi using a simple FileWriter, paying no attention
		  to charsets.  No need for PrintWriter since we already have
		  a string.
		*/
		File f = new File( "pi.default" );
		try {
			FileWriter fw = new FileWriter( f );
			fw.write( pi );
			fw.close();
			System.out.println( f + " file length " + f.length() );
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}		

		/*
		  write out pi with various standard character encodings To
		  get the exact charset we want, we must use an
		  OutputStreamWriter, which accepts a charset object or name
		  of one
		*/
		for( String name : popular ) {
			try {
				f = new File( "pi." + name );
				FileOutputStream fos = new FileOutputStream( f );
				OutputStreamWriter osw = new OutputStreamWriter( fos, name );
				osw.write( pi );
				osw.close();
				System.out.println( name + " file length " + f.length() );
			} catch( UnsupportedEncodingException never ) {
			} catch( IOException ioe ) {
				System.err.println( ioe );
			}
		}

		// and now try to read them back in, a classic roundtrip test
		char[] cs = new char[1];
		for( String name : popular ) {
			try {
				f = new File( "pi." + name );
				FileInputStream fis = new FileInputStream( f );
				InputStreamReader isr = new InputStreamReader( fis, name );
				int n = isr.read( cs );
				String pi2 = new String( cs );
				boolean success = pi.equals( pi2 );
				System.out.println( "Roundtrip " + f + " " + success );
			} catch( UnsupportedEncodingException never ) {
			} catch( IOException ioe ) {
				System.err.println( ioe );
			}
		}
	}
}

// eof

		
		
