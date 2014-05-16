package cp125.week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Stuart Maclean
 *
 * Showing how Java Collections can be nested.  We maintain a Map<K,V>
 * whose key type parameter K is just String but whose value type parameter
 * V is itself a Collection class: List<String>. Thus we have
 * Map<String,List<String>>. Note the double >> at the end. 

 * The keys K are programming language names.  The values V are a list of
 * keywords for that language.
 *
 * The user is then repeatedly prompted for a keyword, and the data set
 * searched for languages containing that keyword, and the results
 * printed.
 
 * Further, we supply four different implementations for the search
 * function.  The four show two ways of iterating over Map content
 * (Map.keySet vs Map.entrySet) and two ways of searching a list
 * (Collections.binarySearch, which expects sorted data, and
 * List.contains, which does not)
 */
 
public class KeywordsByLanguage {

	static public void main( String[] args ) {

		// the data set to be searched, a sample of keywords from 4 languages
		String[] C_keywords = { "do", "while", "for", "if", "else" };
		String[] CPlusPlus_keywords = { "friend", "virtual", "class", "if" };
		String[] Java_keywords = { "interface", "native", "do", "if", "for" };
		String[] Scheme_keywords = { "lambda", "let", "define", "if" };

		/*
		  The map that will hold all the data.  In search terms, this
		  is the haystack
		*/
		Map<String,List<String>> keywordsByLanguage =
			new HashMap<String,List<String>>();

		// Arrays.asList can convert T[] to List<T>
		List<String> lC =   Arrays.asList( C_keywords );
		List<String> lCPP = Arrays.asList( CPlusPlus_keywords );
		List<String> lJ =   Arrays.asList( Java_keywords );
		List<String> lS =   Arrays.asList( Scheme_keywords );

		// In case we use the search method which calls Collections.binarySearch
		Collections.sort( lC );
		Collections.sort( lCPP );
		Collections.sort( lJ );
		Collections.sort( lS );

		// populate the haystack with all the keys and values
		keywordsByLanguage.put( "C", lC );
		keywordsByLanguage.put( "CPlusPlus", lCPP );
		keywordsByLanguage.put( "Java", lJ );
		keywordsByLanguage.put( "Scheme", lS );

		String prompt = "Keyword Lookup Tool.  Enter some words...";
		System.out.println( prompt );
		/*
		  Ask the user for some keywords.  For each, locate and print out
		  all languages in our dataset which contain the supplied keyword.
		  Set.toString suffices in this case
		*/
		BufferedReader br = new BufferedReader
			( new InputStreamReader( System.in ) );
		String line;
		try {
			while( (line = br.readLine()) != null ) {
				line = line.trim();
				if( line.isEmpty() )
					continue;
				String[] words = line.split( "\\s+" );
				String needle = words[0];
				Set<String> langs = languagesContainingKeyword_A
					( needle, keywordsByLanguage );

				// alternative search functions, should produce same result..
				if( false ) {
					Set<String> langsB = languagesContainingKeyword_B
						( needle, keywordsByLanguage );
					Set<String> langsC = languagesContainingKeyword_C
						( needle, keywordsByLanguage );
					Set<String> langsD = languagesContainingKeyword_D
						( needle, keywordsByLanguage );
				}
				// langs in a string concat expression has its toString called
				System.out.println( "Languages containing " + needle +
									" = " + langs );
				
			}
		} catch( IOException ioe ) {
			System.err.println( ioe );
		}
			
	}

	/**
	   This implementation:

	   1: Uses the function Map.keySet() to obtain an iterator for all
	   keys in the supplied map.  The corresponding keyword list must
	   then be looked up via Map.get().

	   2: Uses the function List.contains() to deduce whether the search
	   key (the needle) is a keyword in each language.  This works in
	   O(N), i.e. linear, time.  It does NOT require a sorted input
	   list.

	   @result the set of languages containing the search key (needle)
	   as a keyword.  A set is the correct abstraction here, since
	   there is no real ordering for the result.  Can always convert
	   to list and sort later.
	*/
	   
	static Set<String> languagesContainingKeyword_A
		( String needle, Map<String,List<String>> haystack ) {

		Set<String> result = new HashSet<String>();
		for( String lang : haystack.keySet()){
			List<String> keywords = haystack.get( lang );
			if( keywords.contains( needle ) ) {
				result.add( lang );
			}
					
		}
		return result;
	}

	/**
	   This implementation:

	   1: Uses the function Map.entrySet() to obtain an iterator for
	   all key/pairs. This avoids having to lookup a value given a key.

	   2: Uses the function List.contains() to deduce whether the search
	   key (the needle) is a keyword in each language.  This works in
	   O(N), i.e. linear, time.  It does NOT require a sorted input
	   list.

	   @result the set of languages containing the search key (needle)
	   as a keyword.  A set is the correct abstraction here, since
	   there is no real ordering for the result.  Can always convert
	   to list and sort later.
	*/
	   
	static Set<String> languagesContainingKeyword_B
		( String needle, Map<String,List<String>> haystack ) {

		Set<String> result = new HashSet<String>();
		for( Map.Entry<String,List<String>> me : haystack.entrySet()){
			String lang = me.getKey();
			List<String> keywords = me.getValue();
			if( keywords.contains( needle ) ) {
				result.add( lang );
			}
		}
		return result;
	}

	/**
	   This implementation:

	   1: Uses the function Map.keySet() to obtain an iterator for all
	   keys in the supplied map.  The corresponding keyword list must
	   then be looked up via Map.get().

	   2: Uses the function Collections.binarySearch to deduce whether
	   the search key (the needle) is a keyword in each language.  This works
	   in O(log N) time but MUST be supplied a sorted input list.

	   @result the set of languages containing the search key (needle)
	   as a keyword.  A set is the correct abstraction here, since
	   there is no real ordering for the result.  Can always convert
	   to list and sort later.
	*/
	   
	static Set<String> languagesContainingKeyword_C
		( String needle, Map<String,List<String>> haystack ) {

		Set<String> result = new HashSet<String>();
		for( String lang : haystack.keySet()){
			List<String> keywords = haystack.get( lang );
			int index = Collections.binarySearch( keywords, needle );
			if( index >= 0 ) {
				result.add( lang );
			}
		}
		return result;
	}

	/**
	   This implementation:

	   1: Uses the function Map.entrySet() to obtain an iterator for
	   all key/pairs. This avoids having to lookup a value given a key.

	   2: Uses the function Collections.binarySearch to deduce whether
	   the search key (the needle) is a keyword in each language.  This works
	   in O(log N) time but MUST be supplied a sorted input list.

	   @result the set of languages containing the search key (needle)
	   as a keyword.  A set is the correct abstraction here, since
	   there is no real ordering for the result.  Can always convert
	   to list and sort later.
	*/
	   
	static Set<String> languagesContainingKeyword_D
		( String needle, Map<String,List<String>> haystack ) {

		Set<String> result = new HashSet<String>();
		for( Map.Entry<String,List<String>> me : haystack.entrySet()){
			String lang = me.getKey();
			List<String> keywords = me.getValue();
			int index = Collections.binarySearch( keywords, needle );
			if( index >= 0 ) {
				result.add( lang );
			}
		}
		return result;
	}
}

// eof
