package cp125.week1;

/**
 * @author Stuart Maclean
 *
 * As it stands, this file will (deliberately!) NOT compile.  Use of
 * the identifer (class name) 'List' is ambiguous given the imports.
 * Both packages (java.awt and java.util) contain a class called List.
 */

import java.awt.*;
import java.util.*;

public class ImportClash {

	static public void main( String[] args ) {

		// Comment this out to make this file buildable...
		//List l = new ArrayList();

		/*
		  Given the imports we have above, using the fully-qualified
		  name is all we can do...
		*/
		java.util.List l = new ArrayList();
	}
}

// eof
