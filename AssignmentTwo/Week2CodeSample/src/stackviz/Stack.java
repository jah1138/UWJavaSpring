package stackviz;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stuart Maclean
 *
 *
 * Stack data structure.  This is the 'model' in an MVC architecture.
 * Main.java would then be the 'controller' (takes user inputs) and
 * StackView.java is then the 'view' (shows state of the model).
 *
 * @see Main
 * @see StackView
 */

public class Stack {

	public Stack( int size ) {
		this.size = size;
		frames = new ArrayList<Frame>();
		tos = 0;
	}

	public void setView( StackView v ) {
		view = v;
	}

	/**
	 * @param n - how many units to allocate/push
	 *
	 * @return new top of stack posn.  This the top-most
	 * 'allocated' posn, NOT the first free posn.  Or -1 if the
	 * allocation cannot be done.
	 */
	public int push( int n ) {
		if( size - tos >= n ) {
			Frame f = new Frame( tos+1, n );
			frames.add( f );
			if( view != null ) {
				view.updatePush( f.posn, f.len );
			}
			tos += n;
			return tos;
		}
		return -1;
	}

	/**
	 * @return new top of stack posn.  This the top-most
	 * 'allocated' posn, NOT the first free posn.  Or -1 if the
	 * stack is already empty.
	 */
	public int pop() {
		if( frames.isEmpty() )
			return -1;
		Frame f = frames.remove( frames.size()-1);
		if( view != null ) {
			view.updatePop();
		}
		tos -= f.len;
		return tos;
	}
	
	public void clear() {
		frames.clear();
		tos = 0;
		if( view != null ) {
			view.updateClear();
		}
	}

	@Override
	public String toString() {
		return "Size = " + size + ", Frames " + frames;
	}

	// we allocate a 'chunk' or 'frame' at once, not just one unit...
	class Frame {
		Frame( int posn, int len ) {
			this.posn = posn;
			this.len = len;
		}
		@Override
		public String toString() {
			return "" + posn + " - " + len;
		}
		
		final int posn, len;
	}

	final int size;
	int tos;
	final List<Frame> frames;
	StackView view;
}

// eof
