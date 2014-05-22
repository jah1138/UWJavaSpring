package cp125.week7;

/**
 * @author Stuart Maclean
 *
 * Derive the number of CPUs available on the current execution
 * environment.  This is a runtime lookup, NOT a build time lookup.
 */
public class CPUCount {

	static public void main( String[] args ) {
		Runtime rt = Runtime.getRuntime();
		int cpuCount = rt.availableProcessors();
		System.out.println( "CPUs: " + cpuCount );
	}
}

// eof
