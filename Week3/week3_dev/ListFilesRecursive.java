package cp125.week3;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Stuart Maclean
 *
 * Extend java.io.File.listFiles, both for the operation taking
 * FileFilter as parameter and the operation taking FilenameFilter.
 * The extension is to descend recursive into some directory tree to
 * locate all file matching the filter.  The File.listFiles APIs are
 * NOT recursive (boo)
 */

public class ListFilesRecursive {
	
	/**
	 * Like the Unix command 'find'.  Locate all files under a root
	 * directory matching the supplied name-based filter.  Maintain
	 * the same File[] return type of the core java.io.File.listFiles
	 * (so convert the internal List<File> to File[] on output
	 *
	 * @see #find( java.io.File, java.io.FileFilter )
	 */
	static public File[] find( File root, FilenameFilter ff ) {
		List<File> acc = new ArrayList<File>();
		find( root, ff, acc );
		return acc.toArray( new File[] {} );
	}

	/**
	 * Find every file (and directory) under a given root
	 */
	static public File[] find( File root ) {
		return find( root, ACCEPTALLNAMES );
	}

	/**
	 * The internal implementation for the public methods above
	 * @param acc - the accumulating result
	 */
	static private void find( File dir, FilenameFilter ff, List<File> acc ) {
		File[] fs = dir.listFiles( ff );

		// if dir is not actually a directory, bail early...
		if( fs == null )
			return;

		// add all matched files from this directory to the result...
		for( File f : fs )
			acc.add( f );
		
		// then traverse all subdirs
		File[] ds = dir.listFiles( ISDIRECTORY );
		for( File d : ds ) {
			find( d, ff, acc );
		}
	}

	/**
	 * a version taking FileFilter instead of FilenameFilter
	 *
	 * @see #find( java.io.File, java.io.FilenameFilter )
	 */
	static public File[] find( File root, FileFilter ff ) {
		List<File> acc = new ArrayList<File>();
		find( root, ff, acc );
		return acc.toArray( new File[] {} );
	}

	/**
	 * The internal implementation for the public method above
	 * @param acc - the accumulating result
	 */
	static private void find( File dir, FileFilter ff, List<File> acc ) {
		File[] fs = dir.listFiles( ff );

		// if dir is not actually a directory, bail early...
		if( fs == null )
			return;

		// add all matched files from this directory to the result...
		for( File f : fs )
			acc.add( f );
		
		// then traverse all subdirs
		File[] ds = dir.listFiles( ISDIRECTORY );
		for( File d : ds ) {
			find( d, ff, acc );
		}
	}


	static private final FilenameFilter ACCEPTALLNAMES = new FilenameFilter() {
			public boolean accept( File dir, String name ) {
				return true;
			}
		};
	
	// LOOK: Apache commons-io has many filters...
	static private final FileFilter ISDIRECTORY = new FileFilter() {
			public boolean accept( File pathname ) {
				return pathname.isDirectory();
			}
		};
}

// eof