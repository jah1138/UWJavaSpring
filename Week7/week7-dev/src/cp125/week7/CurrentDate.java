package cp125.week7;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Stuart Maclean
 *
 * Derive the number of milliseconds from the 'Unix Epoch' (which is
 * Jan 1 1970 00:00:00 UTC/GMT) as a java.util.Date object, as
 * retrieved from local system clock. Then print the Date to stdout.
 * Also show how to construct a Date from some other millis value,
 * e.g. 0.
 */
public class CurrentDate {

	static public void main( String[] args ) {
		// no args constructor gives a Date object representing 'now'
		Date sinceEpoch = new Date();
		System.out.println( "Now  : " + sinceEpoch );

		// can also build a Date with any other millis value
		Date theEpoch = new Date( 0L );
		System.out.println( "Epoch: " + theEpoch );

		/*
		  The above Epoch likely still prints out according to the
		  local time zone, which is Pacific Standard Time (PST) should
		  my computer's timezone be set to Seattle. We need a TimeZone
		  and a SimpleDateFormat object to force printing in a different
		  timezone.  This is more complex than dealing with just the Date
		  class, but more flexible.  See also java.util.Calendar
		*/
		TimeZone utc = TimeZone.getTimeZone( "UTC" );
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone( utc );
		System.out.println( "Epoch UTC: " + sdf.format( theEpoch ) );
		  
		
	}
}

// eof
