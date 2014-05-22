package cp125.week7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * @author Stuart Maclean
 *
 * Mimic a chat program with another user by chatting with 'the
 * console'.  The console has its own thread which periodically prints
 * some text, indpendently of what the user is doing.
 */
public class ConsoleChat {

	static public void main( String[] args ) {

		Thread consoleThread = new ConsoleThread();

		if( false ) {
			consoleThread.setDaemon( true );
		}
		
		consoleThread.start();

		InputStreamReader isr = new InputStreamReader( System.in );
		BufferedReader br = new BufferedReader( isr );
		try {
			while( true ) {
				String line = br.readLine();
				if( line == null )
					break;
				System.out.println( "I'll tell X: " + line );
			}
		} catch( IOException ioe ) {
		}
		
	}

	static class ConsoleThread extends Thread {
		@Override
		public void run() {
			int i = 1;
			while( true ) {
				System.out.println( "X said blah blah " + i );
				// sleep somewhere between 0 and 5 seconds...
				long sleepTime = 5 * (long)(1000 * Math.random() );
				try {
					Thread.sleep( sleepTime );
				} catch( InterruptedException ie ) {
				}
				i++;
			}
		}
	}
			
}
// eof