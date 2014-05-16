package cp125.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stuart Maclean
 *
 * A simple interactive program for investigating operations such as
 *
 * put
 * get
 * containsKey
 * remove
 *
 * on Map<String,Point>, where class Point is defined locally (in this
 * code bundle) and the String key is some name for the point,
 * e.g. "origin" -> (0,0)
 */

public class PointMaps {

	static public void main( String[] args ) {

		String ls = System.getProperty( "line.separator" );
		String help = "p name x y - put," + ls +
			"c name - containsKey, g name - get/lookup, p - print," + ls +
			"r name - remove, s - size";

		String prompt = "> ";
		
		String word = "\\w+";
		String decimal = "\\d+";
		String whitespace = "\\s+";

		Pattern pPutNameXY = Pattern.compile
			( "p" + whitespace + "(" + word + ")" + whitespace +
			  "(" + decimal + ")" + whitespace + "(" + decimal + ")" );

		Pattern pGetName = Pattern.compile
			( "g" + whitespace + "(" + word + ")" );

		Pattern pContainsKey = Pattern.compile
			( "c" + whitespace + "(" + word + ")" );

		Pattern pRemove = Pattern.compile
			( "r" + whitespace + "(" + word + ")" );

		Map<String,Point> ps = new HashMap<String,Point>();
		
		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader br = new BufferedReader( isr );
		String line = null;
		try {
			while( true ) {
				System.out.print( prompt );
				line = br.readLine();
				if( line == null )
					break;
				line = line.trim();
				if( line.length() == 0 )
					continue;

				if( false ) {
				} else if( line.equals( "h" ) ) {
					System.out.println( help );
					continue;
				} else if( line.equals( "p" ) ) {
					/*
					  See how the Map calls toString on its keys AND
					  values and adds "," between elements,
					  as well as adding [ and ].
					*/
					System.out.println( ps );
					continue;
				} else if( line.equals( "s" ) ) {
					System.out.println( ps.size() );
					continue;
				}
				
				// was the user input a 'put name x y'
				Matcher m = pPutNameXY.matcher( line );
				if( m.matches() ) {
					String name = m.group(1);
					int x = Integer.parseInt( m.group(2) );
					int y = Integer.parseInt( m.group(3) );
					Point p = new Point( x, y );
					ps.put( name, p );
					continue;
				}

				// was the user input a 'contains name'?
				m = pContainsKey.matcher( line );
				if( m.matches() ) {
					String needle = m.group(1);
					boolean b = ps.containsKey( needle );
					System.out.println( b );
					continue;
				}

				// was the user input a 'get name'?
				m = pGetName.matcher( line );
				if( m.matches() ) {
					String needle = m.group(1);
					Point located = ps.get( needle );
					System.out.println( located );
					continue;
				}

				// was the user input a 'remove name'?
				m = pRemove.matcher( line );
				if( m.matches() ) {
					String needle = m.group(1);
					Point removed = ps.remove( needle );
					System.out.println( removed );
					continue;
				}
				
				System.out.println( "Unknown: " + line );
			}
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
		System.out.println();
	}
}

// eof

		