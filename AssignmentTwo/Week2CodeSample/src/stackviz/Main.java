package stackviz;

import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * @author Stuart Maclean
 *
 * An interactive Stack Visualization Tool.  User enters commands
 * to a command line menu and stack graphic window updates accordingly.
 *
 * Run with no args for a default stack capacity or use args[0] for
 * user chosen one.
 *
 * Stack allocations are in 'chunks', much like would be done for
 * stack frame allocations needed for method calls in a real Java
 * program.
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
		Stack s = new Stack( size );

		JFrame jf = new JFrame( "Stack, Size " + size);
		jf.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		StackView sv = null;
		Dimension d = null;
		if( false ) {
			sv = new StackView.Horizontal(size);
			d = new Dimension( 800, 200 );
		} else {
			sv = new StackView.Vertical(size);
			d = new Dimension( 200, 800 );
		}
		sv.setPreferredSize( d );
		s.setView( sv );
		jf.getContentPane().add( sv );
		jf.pack();
		jf.setVisible( true );

		System.out.println
			( "Welcome to StackViz.  Green means free, red allocated." );

		String menu = "push/u N to push, pop/o, p to print, c to clear, q to quit:";
		System.out.println( menu );

		// No jline? Pah!
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
				System.out.println( s );
			} else if( cmd.equals( "c" ) || cmd.equals( "clear" ) ) {
				s.clear();
				sv.repaint();
			} else if( cmd.equals( "u" ) ||
					   cmd.equals( "pu" ) ||
					   cmd.equals( "pus" ) ||
					   cmd.equals( "push" ) ) {
				if( toks.length < 2 ) {
					System.out.println( "usage: push N" );
					continue;
				}
				String nS = toks[1];
				int n = Integer.parseInt( nS );
				if( n < 1 ) {
					System.out.println( "Out-of-bounds: " + n );
					continue;
				}
				int tos = s.push( n );
				if( tos == -1 )
					System.out.println( "Insufficient Space" );
				else
					System.out.println( "Top of Stack: " + tos );
				sv.repaint();
			} else if( cmd.equals( "o" ) ||
					   cmd.equals( "po" ) ||
					   cmd.equals( "pop" ) ) {
				int tos = s.pop();
				if( tos == -1 )
					System.out.println( "Stack Empty" );
				else 
					System.out.println( "Top of Stack: " + tos );
				sv.repaint();
			} else {
				System.out.println( "Unknown Command: " + cmd );
			}
					
		}
		System.exit(0);
	}

	static final String WHITESPACE = "\\s+";
}

// eof

	