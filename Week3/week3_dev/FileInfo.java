package cp125.week3;

import java.io.File;
import java.io.IOException;

/**
 * @author Stuart Maclean
 *
 * For the file name supplied in args[0], use class java.io.File to derive
 * various properties of that file:
 *
 * does it exist?
 * its type: file vs directory, or other
 * permissions: can we read, write, execute it?
 * name and path (absolute name)
 * its parent file
 *
 * We also show how to 'canonicalise' a File, meaning to derive its absolute
 * path, even if the name supplied was just a relative name.
 */
public class FileInfo {

	static public void main( String[] args ) {
		String usage = FileInfo.class.getName() + " fileName";
		if( args.length < 1 ) {
			System.err.println( usage );
			System.exit(1);
		}

		String name = args[0];
		File f = new File( name );
		if( f.exists() ) {
			info( f );
			try {
				File canonical = f.getCanonicalFile();
				if( !f.equals( canonical ) )
					info( canonical );
			} catch( IOException ioe ) {
				System.err.println( ioe );
			}
		} else {
			System.out.println( name + ": no such file or directory" );
		}
		 
	}

	static public void info( File f ) {
		System.out.println();
		System.out.println( "Name: " + f.getName() );
		System.out.println( "Path: " + f.getPath() );
		System.out.println( "IsFile: " + f.isFile() );
		System.out.println( "IsDirectory: " + f.isDirectory() );
		System.out.println( "Parent: " + f.getParentFile() );
		System.out.println( "Readable: " + f.canRead() );
		System.out.println( "Writable: " + f.canWrite() );
		System.out.println( "Executable: " + f.canExecute() );
		System.out.println( "Length: " + f.length() );
		System.out.println();
	}
		
}

// eof
