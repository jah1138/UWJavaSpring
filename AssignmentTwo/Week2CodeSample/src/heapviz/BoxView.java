package heapviz;

/**
 * @author Stuart Maclean
 *
 * An interactive Heap Visualization Tool.  User enters commands
 * to a command line menu and heap graphic window updates accordingly.
 */

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BoxView extends JComponent {

	public BoxView( Color c ) {
		color = c;
		setForeground( Color.white );
		setBackground( c );
		setOpaque( true );
		//		setDoubleBuffered( true );
	}

	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		System.out.println( "BoxView: " + isDoubleBuffered() );
		g.setColor( getBackground() );
		g.fillRect( 0, 0, getWidth(), getHeight() );
		g.setColor( getForeground() );
		g.drawString( "" + getX(), 5,25 );
	
	}

	public void addNotify( ) {
		super.addNotify();
		System.out.println( "BoxView: " + getBounds() );
	
	}

	public void setBounds( int x, int y, int width, int height ) {
		super.setBounds( x, y, width, height );
	}

	Color color;
}

// eof
