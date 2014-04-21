package cp125.week2;

/**
 * @author Stuart Maclean
 *
 * Showing how OutOfMemoryError is not fatal but can be caught and
 * an alternative action taken.
 *
 * OutOfMemoryError is perhaps a misnomer, as it suggests 'no more
 * memory, cannot continue', much like 'the car is out of gas, cannot
 * continue'.
 *
 * A better name might have been 'InsufficentSpaceForAllocation',
 * which implies that this one heap allocation failed, but that others
 * might work, or that at least the program is able to continue if it
 * can make do without the new chunk.  We could even free some other
 * chunk and re-try the failing request.
 *
 * To make this program more interesting, try invoking it with
 * different maximum heap sizes.  Consult your runtime's docs for how
 * to do this (typically a -X option)
 *
 * Note: We are using an int array here.  For the purposes of memory
 * allocation, arrays are like objects.  They are always allocated on
 * the heap.  A Java array is never a local, stack-based, creation.
 */

public class AdjustToHeap {

	static public void main( String[] unused ) {

		int howManyInts = 1;

		while( true ) {
			try {
				int[] intArray = new int[howManyInts];
				howManyInts *= 2;
			} catch( OutOfMemoryError oome ) {
				break;
			}
		}
		System.out.println( "Able to create " + howManyInts + " ints..." );

		/*
		  If howManyInts is 'enough', our program might be able to
		  work.  If it can, you can realistically say that your
		  program has 'adjusted' to the heap and thus the VM
		  environment is which it is to run.
		*/
	}
}

// eof

		