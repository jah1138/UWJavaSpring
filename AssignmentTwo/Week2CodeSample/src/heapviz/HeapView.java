package heapviz;

import java.awt.*;
import javax.swing.*;

/**
 * @author Stuart Maclean
 *
 * The view component(s) of a MVC architecture.
 */

abstract public class HeapView extends JPanel {
	
	protected HeapView( int size ) {
		//setBorder( BorderFactory.createLineBorder( Color.black ) );
		this.size = size;
		setBackground( Color.GREEN );
		setLayout( null );
	}
		
	public abstract void updateAdd( int posn, int len );

	public void updateRemove( int posn ) {
		for( Component c : getComponents() ) {
			AllocView v = (AllocView)c;
			if( v.posn == posn ) {
				remove( v );
				return;
			}
		}
	}

	public void updateClear() {
		removeAll();
	}
			
	static class Vertical extends HeapView {
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
		public void updateAdd( int posn, int len ) {
			AllocView av = new AllocView.Vertical( posn, len );
			int y = (int)((posn+len) * sy + ty);
			int h = (int)(len * -sy);
			av.setBounds( 0, y, getWidth(), h );
			add( av );
		}
		
		double sy, ty;
	}

	static class Horizontal extends HeapView {
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
		public void updateAdd( int posn, int len ) {
			AllocView av = new AllocView.Horizontal( posn, len );
			int x = (int)(posn * sx + tx);
			int w = (int)(sx * len);
			av.setBounds( x, 0, w, getHeight() );
			add( av );
		}
		
		double sx, tx;
	}


    static final Font font = new Font( "Monospaced", Font.PLAIN,
									   Main.FONTSIZE );  

	static class AllocView extends JComponent {
		AllocView( int posn, int len ) {
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
		
		static class Vertical extends AllocView {
			Vertical( int posn, int len ) {
				super( posn, len );
			}
			public void paintComponent( Graphics g ) {
				super.paintComponent( g );
				g.setColor( getForeground() );
				g.drawString( "" + posn, 5, getHeight() - 5 );
			}
		}

		static class Horizontal extends AllocView {
			Horizontal( int posn, int len ) {
				super( posn, len );
			}
			public void paintComponent( Graphics g ) {
				super.paintComponent( g );
				g.setColor( getForeground() );
				g.drawString( "" + posn, 5, 5 );
			}
		}

		final int posn, len;
	}

	final int size;
}

// eof
