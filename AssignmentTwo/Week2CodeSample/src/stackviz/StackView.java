package stackviz;

import java.awt.*;
import javax.swing.*;

/**
 * @author Stuart Maclean
 *
 *
 * StackView, a simple Swing panel. We offer options for horizontal
 * and vertical orientated views.
 *
 * @see Main
 * @see Stack
 */

abstract public class StackView extends JPanel {

	protected StackView( int size ) {
		//setBorder( BorderFactory.createLineBorder( Color.black ) );
		this.size = size;
		setBackground( Color.GREEN );
		setLayout( null );
	}
		
	public abstract void updatePush( int posn, int len );

	public void updatePop() {
		int n = getComponentCount();
		remove( n-1 );
	}

	public void updateClear() {
		removeAll();
	}

	/**
	 * A View in 'portrait mode'
	 */
	static class Vertical extends StackView {
		Vertical( int size ) {
			super( size );
		}

		@Override
		public void setBounds( int x, int y, int w, int h ) {
			super.setBounds( x, y, w, h );
			sy = ((double)h)/-size;
			ty = h + (double)h/size;
		}

		@Override
		public void updatePush( int posn, int len ) {
			FrameView fv = new FrameView.Vertical( posn, len );
			int y = (int)((posn+len) * sy + ty);
			int h = (int)(len * -sy);
			fv.setBounds( 0, y, getWidth(), h );
			add( fv );
		}
		
		double sy, ty;
	}

	/**
	 * A View in 'landscape mode'
	 */
	static class Horizontal extends StackView {
		Horizontal( int size ) {
			super( size );
		}

		@Override
		public void setBounds( int x, int y, int w, int h ) {
			super.setBounds( x, y, w, h );
			sx = (double)w/size;
			tx = (double)-w/size;
		}

		@Override
		public void updatePush( int posn, int len ) {
			FrameView fv = new FrameView.Horizontal( posn, len );
			int x = (int)(posn * sx + tx);
			int w = (int)(sx * len);
			fv.setBounds( x, 0, w, getHeight() );
			add( fv );
		}
		
		double sx, tx;
	}

    static final Font font = new Font( "Monospaced", Font.PLAIN,
									   Main.FONTSIZE );  

	static class FrameView extends JComponent {
		FrameView( int posn, int len ) {
			setFont( font );
			setBorder( BorderFactory.createLineBorder( Color.black ) );
			this.posn = posn;
			this.len = len;
			setForeground( Color.white );
			setBackground( Color.red );
			setOpaque( true );
			//		setDoubleBuffered( true );
		}
		public void paintComponent( Graphics g ) {
			super.paintComponent( g );
			//			System.out.println( "AllocView: " + getBounds() );
			g.setColor( getBackground() );
			g.fillRect( 0, 0, getWidth(), getHeight() );
		}
		
		static class Vertical extends FrameView {
			Vertical( int posn, int len ) {
				super( posn, len );
			}
			public void paintComponent( Graphics g ) {
				super.paintComponent( g );
				g.setColor( getForeground() );
				g.drawString( "" + (posn+len-1), 5, 25 );
			}
		}

		static class Horizontal extends FrameView {
			Horizontal( int posn, int len ) {
				super( posn, len );
			}
			public void paintComponent( Graphics g ) {
				super.paintComponent( g );
				g.setColor( getForeground() );
				g.drawString( "" + (posn+len-1), 5, 5 );
			}
		}

		final int posn, len;
	}

	final int size;
}

// eof
