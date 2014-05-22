package cp125.week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Stuart Maclean.
 *
 * A template for assignment 6.  We load a transcript file of some
 * text attributed to two chat users Bill and Ben.  We then want to
 * answer 3 questions about each of the two users:

 * Longest word said
 *
 * Shortest word said
 *
 * Whether the user said any particular words, to be supplied.
 *
 * For 'longest' and 'shorest' words, don't worry about identifying
 * ALL of the longest, shortest ones ,we just pick one and say 'no word
 * was longer than this', etc.
 */
 
public class TranscriptProcessing {

	static public void main( String[] args ) {

        String path = System.getProperty("user.home") + "\\";
        String filename = "transcript.txt";
		File f = new File( path + filename );
//        File f = new File( "transcript.txt" );  // <<< original
		if( !( f.isFile() && f.canRead() ) ) {
			System.err.println( "Cannot load: " + f );
			System.exit(1);
		}

		List<String> allWordsBill = new ArrayList<String>();
		List<String> allWordsBen  = new ArrayList<String>();

		// load the data from the file...
		loadTranscript( f, allWordsBill, allWordsBen );

		// and print out what we've got...
		System.out.println( "Bill:" );
		System.out.println( allWordsBill );
		System.out.println();
//		System.out.println();
		System.out.println( "Ben:" );
		System.out.println( allWordsBen );
        System.out.println();

		// sort the data and identify longest and shortest words said...
		sorting( "Bill", allWordsBill );
		sorting( "Ben", allWordsBen );

		// search the data for particular words said...
		String[] didBillSay = { "written", "brackets", "count", "Python" };
		searching( "Bill", allWordsBill, didBillSay );
		String[] didBenSay = { "Sounders", "Blah", "blah", "funny" };
		searching( "Ben", allWordsBen, didBenSay );
	}

	static void sorting( String who, List<String> words ) {

		Collections.sort(words, new Comparator<String>() {
            public int compare(String s1, String s2) {
                int result = 0;
                if (s1.length() > s2.length()) {
                    result = 1;
                } else if (s1.length() < s2.length()) {
                    result = -1;
                }
                return result;
            }
        });

		String longest = words.get(words.size() - 1);
		String shortest = words.get(0);
		System.out.println( who + " longest  word = " + longest );
		System.out.println( who + " shortest word = " + shortest );
        System.out.println();
    }

	static void searching( String who, List<String> words,
						   String[] needles ) {

        Collections.sort(words);
        for (String needle : needles) {
            Boolean result = Boolean.FALSE;
            if (Collections.binarySearch(words, needle) >= 0) {
                result = Boolean.TRUE;
            }
            System.out.println( who + " said " + needle + ": " + result );
        }
        System.out.println();
    }

	static void loadTranscript( File f,
								List<String> billsWords,
								List<String> bensWords ) {
		try {
			LineNumberReader lnr = new LineNumberReader
				( new FileReader( f ) );
			String line;
			while( (line = lnr.readLine()) != null ) {
				String spoken = null;
				List<String> whichList = null;
				if( false ) {
					// this makes the following else/ifs line up nice!
				} else if( line.startsWith( "Bill:" ) ) {
					// I prefer "Bill:".length() over just '5'
					spoken = line.substring( "Bill:".length() );
					whichList = billsWords;
				} else if( line.startsWith( "Ben:" ) ) {
					spoken = line.substring( "Ben:".length() );
					whichList = bensWords;
				} else {
					// hmm, bad line, discard.
					continue;
				}
				spoken = spoken.trim();
				String[] words = spoken.split( "\\s+" );
				for( String word : words ) {
					// We don't care about punctuation...
//					word = word.replaceAll( "\\p{Punct}", "" );
                    word = word.replaceAll("[.,:;!?/(/)]", "");  // I like to keep apostrophes
					whichList.add( word );
				}
			}
			lnr.close();
		} catch( IOException ioe ) {
			System.err.println( ioe );
			System.exit(1);
		}
	}
}

// eof
