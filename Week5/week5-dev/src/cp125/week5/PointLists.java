package cp125.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Stuart Maclean
 *
 * A simple interactive program for investigating operations such as
 *
 * add
 * remove
 * contains
 *
 * on List<Point>, where class Point is defined locally (in this code bundle!)
 */


public class PointLists {

	static public void main( String[] args ) {

		String ls = System.getProperty( "line.separator" );
		String help = "a x y n? - add(s), ar n? - add random(s)," + ls +
			"c x y - contains, p - print," + ls +
			"r x y - remove, ri i - removeAt, s - size";

		String prompt = "> ";
		
		String decimal = "\\d+";
		String whitespace = "\\s+";

		Pattern pAddXY = Pattern.compile
			( "a" + whitespace +
			  "(" + decimal + ")" + whitespace + "(" + decimal + ")" +
			  "(" + whitespace + decimal + ")?" );

		Pattern pAddRandom = Pattern.compile
			( "ar" + "(" + whitespace + decimal + ")?" );

		Pattern pContains = Pattern.compile
			( "c" + whitespace +
			  "(" + decimal + ")" + whitespace + "(" + decimal + ")" );

		Pattern pRemove = Pattern.compile
			( "r" + whitespace + 
			  "(" + decimal + ")" + whitespace + "(" + decimal + ")" );
		
		Pattern pRemoveAt = Pattern.compile
			( "ri" + whitespace + "(" + decimal + ")" );
		

		List<Point> ps = new ArrayList<Point>();
		
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
					  See how the List calls toString on its elements
					  and adds "," between elements, as well as adding
					  [ and ].  
					*/
					System.out.println( ps );
					continue;
				} else if( line.equals( "s" ) ) {
					System.out.println( ps.size() );
					continue;
				}
				
				// was the user input an 'add x,y N?'
				Matcher m = pAddXY.matcher( line );
				if( m.matches() ) {
					int x = Integer.parseInt( m.group(1) );
					int y = Integer.parseInt( m.group(2) );
					int n = 1;
					String nS = m.group(3);
					if( nS != null )
						n = Integer.parseInt( nS.trim() );
					for( int i = 1; i <= n; i++ ) {
						Point p = new Point( x, y );
						ps.add( p );
					}
					continue;
				}

				// was the user input an 'add r N?'
				m = pAddRandom.matcher( line );
				if( m.matches() ) {
					int n = 1;
					String nS = m.group(1);
					if( nS != null )
						n = Integer.parseInt( nS.trim() );
					for( int i = 1; i <= n; i++ ) {
						Point p = Point.random();
						ps.add( p );
					}
					continue;
				}

				// was the user input a 'contains P'?
				m = pContains.matcher( line );
				if( m.matches() ) {
					int needleX = Integer.parseInt( m.group(1) );
					int needleY = Integer.parseInt( m.group(2) );
					// Note how we have to create a needle!
					Point needle = new Point( needleX, needleY );
					boolean b = ps.contains( needle );
					System.out.println( b );
					continue;
				}

				// was the user input a 'remove P'?
				m = pRemove.matcher( line );
				if( m.matches() ) {
					int needleX = Integer.parseInt( m.group(1) );
					int needleY = Integer.parseInt( m.group(2) );
					// Note how we have to create a needle!
					Point needle = new Point( needleX, needleY );
					boolean b = ps.remove( needle );
					System.out.println( b );
					continue;
				}

				// was the user input a 'removeAt i'?
				m = pRemoveAt.matcher( line );
				if( m.matches() ) {
					int i = Integer.parseInt( m.group(1) );
					Point removed = ps.remove( i );
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

		