package heapviz;

import java.util.ArrayList;
import java.util.List;

public class Heap {

	public Heap( int size ) {
		this.size = size;
		allocs = new ArrayList<Alloc>();
	}

	public void setView( HeapView v ) {
		view = v;
	}

	/**
	 * Try to alloc n units on the heap.  Use firstFit. TODO: bestFit
	 */
	public int alloc( int n ) {
		return firstFit( n );
	}

	private void add( Alloc a ) {
		allocs.add( a );
		if( view != null ) {
			view.updateAdd( a.posn, a.len );
		}
	}

	private void insert( Alloc a, int i ) {
		allocs.add( i, a );
		if( view != null ) {
			view.updateAdd( a.posn, a.len );
		}
	}

	private void remove( Alloc a ) {
		allocs.remove( a );
		if( view != null ) {
			view.updateRemove( a.posn );
		}
	}
	
	public void clear() {
		allocs.clear();
		if( view != null ) {
			view.updateClear( );
		}
	}

	@Override
	public String toString() {
		return "Size = " + size + ", " + allocs;
	}
	
	private int firstFit( int n ) {
		int min = 1;
		for( int i = 0; i < allocs.size(); i++ ) {
			Alloc a = allocs.get(i);
			if( a.posn - min >= n ) {
				Alloc aNew = new Alloc( min, n );
				insert( aNew, i );
				return min;
			}
			min = a.posn + a.len;
		}
		if( size + 1 - min >= n ) {
			Alloc aNew = new Alloc( min, n );
			add( aNew );
			return min;
		}
		return -1;
	}

	private int bestFit( int n ) {
		throw new IllegalStateException( "TODO: bestFit allocation" );
	}
	

	/**
	 * @param posn - location of the Alloc to free, and return its
	 * memory back to the unused part of the heap
	 *
	 * @return 0 if the position to free really just refer to an allocated
	 * part of the heap (i.e. an Alloc), -1 otherwise signifying error
	 */
	public int free( int posn ) {
		for( Alloc a : allocs ) {
			if( a.posn == posn ) {
				remove( a );
				return 0;
			}
		}
		return -1;
	}

	/**
	   Alloc is a chunk of allocated memory on the heap.  It has a
	   location and size.  In a Java VM, this would be some kind of
	   'object'.  The allocated state of the heap is then a list of
	   these Allocs.
	*/
	class Alloc {
		Alloc( int posn, int len ) {
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
	final List<Alloc> allocs;
	HeapView view;

	// local testing.  For main app, use Main.java
	static public void main( String[] args ) {
		Heap h = new Heap( 16 );
		System.out.println( "HeapSize: " + h.size );
		int posn1 = h.alloc( 16 );
		System.out.println( "Add 16 = " + posn1 );
		int posn2 = h.alloc( 16 );
		System.out.println( "Add 16 = " + posn2 );
		h.clear();
		int posn3 = h.alloc( 16 );
		System.out.println( "Add 16 = " + posn3 );
		h.clear();
		for( int i = 1; i <= 16; i++ ) {
			int posn = h.alloc( i );
			System.out.println( "Add " + i + " = " + posn );
		}
		h.clear();
		for( int i = 1; i <= 16; i++ ) {
			int posn = h.alloc( 1 );
			System.out.println( "Add 1 = " + posn );
			h.free( posn );
		}
		h.clear();
	}
}

// eof
