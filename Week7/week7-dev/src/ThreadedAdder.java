/**
 * @author Stuart Maclean
 *
 * Split a numeric computation into threads in an attempt to have the
 * calculation run 'faster' compared to coding/running it
 * sequentially.  For this example, 'the computation' is summing all
 * the natural numbers from one to 100 billion.
 */

public class ThreadedAdder {

	static public void main( String[] args ) {

		//		long N = 100,000,000,000 = 1e11 = 100 billion
		long N = 100000000000L;
		System.out.println( "N: " + N );
		
		Runtime rt = Runtime.getRuntime();
		int cpuCount = rt.availableProcessors();
		System.out.println( "CPUs: " + cpuCount );

		int threads = cpuCount;
		if( args.length > 0 )
			threads = Integer.parseInt( args[0] );
		System.out.println( "Threads: " + threads );

		long perThread = N / threads;
		System.out.println( "Adds per thread: " + perThread );

		AdderThread[] ts = new AdderThread[threads];
		long min = 1;
		long max = perThread;

		for( int i = 0; i < threads; i++ ) {
			System.out.println( "Thread " + (i+1) + " " +
								min + " -> " +max );
			AdderThread at = new AdderThread( min, max );
			ts[i] = at;
			at.start();
			min += perThread;
			max += perThread;
		}

		long sum = 0;
		for( int i = 0; i < ts.length; i++ ) { 
			AdderThread at = ts[i];
			try {
				at.join();
				sum += at.sum;
			} catch( InterruptedException neverInThisCode ) {
			}
		}
		System.out.println( "Sum 1.." + N + " = " + sum );
	}

	/*
	  A thread whose run method is simply to add the numbers from
	  'min' to 'max', where those values are passed at construct time.
	  Result stored in 'sum'
	*/
	static class AdderThread extends Thread {
		AdderThread( long min, long max ) {
			this.min = min;
			this.max = max;
			sum = 0;
		}
		@Override
		public void run() {
			for( long i = min; i <= max; i++ )
				sum += i;
		}

		long min, max;
		long sum;
	}
}

// eof

