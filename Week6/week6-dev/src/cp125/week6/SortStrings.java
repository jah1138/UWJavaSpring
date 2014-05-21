package cp125.week6;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Stuart Maclean
 *
 * Create list of strings and sort using Collections.sort.  Printing
 * the list will then show us the logic behind String.compareTo.
 */
 
public class SortStrings {

	static public void main( String[] args ) {
		
		String[] ss = { "java", "Java", "lisp", "Lisp", "C++" };
		List<String> l =  Arrays.asList( ss );
		Collections.sort( l );
		System.out.println( l );

		// Can also sort the array directly...
		Arrays.sort( ss );

		// Likely NOT what you want
		System.out.println( ss );

		// Need a helper function to make a nice printable string
		System.out.println( Arrays.toString( ss ) );
		
	}
}

/**
 * Found this in the JDK source for String.compareTo( String s )
 *
 * @param   anotherString   the <code>String</code> to be compared.
 * @return  the value <code>0</code> if the argument string is equal to
 *          this string; a value less than <code>0</code> if this string
 *          is lexicographically less than the string argument; and a
 *          value greater than <code>0</code> if this string is
 *          lexicographically greater than the string argument.

  public int compareTo(String anotherString) {
   int len1 = value.length;
   int len2 = anotherString.value.length;
   int lim = Math.min(len1, len2);
   char v1[] = value;
   char v2[] = anotherString.value;

        int k = 0;
        while (k < lim) {
            char c1 = v1[k];
            char c2 = v2[k];
            if (c1 != c2) {
                return c1 - c2;
            }
            k++;
        }
        return len1 - len2;
    }
 */

// eof
