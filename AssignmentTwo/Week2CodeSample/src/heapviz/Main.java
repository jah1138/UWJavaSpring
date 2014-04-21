package heapviz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.*;
import javax.swing.*;


/**
 * @author Stuart Maclean
 *
 * An interactive Heap Visualization Tool.  User enters commands
 * to a command line menu and heap graphic window updates accordingly.
 *
 * Run with no args for a default heap capacity or use args[0] for
 * user chosen one.
 *
 * Heap allocations are in 'chunks', much like would be done for
 * object allocation in a real Java program.
 *
 * In a language like Java with 'garbage collection', the free-ing of
 * heap chunks is performed by the Java runtime itself.  Contrast this
 * with a langauge without GC, like C or C++. Then the programmer has
 * to manage the heap much more closely and free things explicitly,
 */

public class Main {

	static public int FONTSIZE = 20;

	static public void main( String[] args ) throws Exception {
		int size = 16;
		if( args.length > 0 ) {
			try {
				size = Integer.parseInt( args[0] );
			} catch( NumberFormatException nfe ) {
			}
		}
		Heap h = new Heap( size );

		JFrame jf = new JFrame( "Heap, Size " + size);
		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		HeapView hv = null;
		Dimension d = null;
		if( false ) {
			hv = new HeapView.Horizontal(size);
			d = new Dimension( 800, 200 );
		} else {
			hv = new HeapView.Vertical(size);
			d = new Dimension( 200, 800 );
		}
		hv.setPreferredSize( d );
		h.setView( hv );
		//		hv.setInsets( new Insets( 10, 10, 10, 10 ) );
		jf.getContentPane().add( hv );
		jf.pack();
		jf.setVisible( true );

		System.out.println
			( "Welcome to HeapViz.  Green means free, red allocated." );

		String menu = "n N to allocate, f N to free, p to print, c to clear, q to quit:";
		System.out.println( menu );


		BufferedReader br = new BufferedReader
			( new InputStreamReader( System.in ) );
		String prompt = "> ";
		while( true ) {
			System.out.print( prompt );
			String line = br.readLine();
			if( line == null )
				break;
			line = line.trim();
			String[] toks = line.split( WHITESPACE );
			String cmd = toks[0];
			if( false ) {
			} else if( cmd.equals( "q" ) || cmd.equals( "quit" ) ) {
				break;
			} else if( cmd.equals( "p" ) || cmd.equals( "print" ) ) {
				System.out.println( h );
			} else if( cmd.equals( "c" ) || cmd.equals( "clear" ) ) {
				h.clear();
				hv.repaint();
			} else if( cmd.equals( "n" ) || cmd.equals( "new" ) ) {
				if( toks.length < 2 ) {
					System.out.println( "Usage: new N" );
					continue;
				}
				String nS = toks[1];
				int n = Integer.parseInt( nS );
				if( n < 1 ) {
					System.out.println( "Out-of-bounds: " + n );
					continue;
				}
				int posn = h.alloc( n );
				if( posn == -1 )
					System.out.println
						( "Insufficient Space (aka Out Of Memory)" );
				else
					System.out.println( "Position: " + posn );
				hv.repaint();
			} else if( cmd.equals( "f" ) || cmd.equals( "free" ) ) {
				if( toks.length < 2 ) {
					System.out.println( "Usage: free posn" );
					continue;
				}
				String posnS = toks[1];
				int posn = Integer.parseInt( posnS );
				if( posn < 1 || posn > size ) {
					System.out.println( "Out-of-bounds: " + posn );
					continue;
				}
				int sc = h.free( posn );
				if( sc == 0 ) 
					System.out.println( "Freed: " + posn );
				else
					System.out.println( "No Allocation: " + posn );
				hv.repaint();
			}
		}
		System.exit(0);
	}

	static final String WHITESPACE = "\\s+";
}

// eof

	