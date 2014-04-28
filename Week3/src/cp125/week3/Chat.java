package cp125.week3;
import java.io.*;

/**
 * Class to read a line of text from the console, record the line number and
 * number of words, and print this information back to the console.
 *
 * Author: Alex Harris
 * Version: 4/28/2014
 */
public class Chat {

    public static void main(String[] args){

        PrintStream ps = System.out;
        InputStream in = System.in;
        InputStreamReader strReader = new InputStreamReader(in);
        LineNumberReader lnReader = new LineNumberReader(strReader);
        String user = System.getProperty("user.name");

        int longest = 0;
        String longWord = null;
        try {
            ps.print("Hello " + user + ": ");
            String line = lnReader.readLine();
            while (!line.equalsIgnoreCase("exit") && !line.equalsIgnoreCase("quit")) {
                int lineNumber = lnReader.getLineNumber();
                String[] words = line.trim().split("[,.:;!()\\s]+");
                int numWords = words.length;
                for (String word : words) {
                    if (word.length() > longest) {
                        longest = word.length();
                        longWord = word;
                    }
                }
                ps.println("Line " + lineNumber + ", " + numWords + " words: you said \"" + line + "\"");
                ps.flush();
                ps.print("Hello " + user + ": ");
                line = lnReader.readLine();
            }
            if (!(longWord == null) && !longWord.equals("")) {
                ps.println("The longest word you used was \"" + longWord + "\".");
            }
        }
        catch (IOException ioe) {
            System.out.println("IO exception: " + ioe);
        }
        ps.close();
//        ps.println("Hey, this PrintStream is supposed to be closed!");
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing InputStream.");
            System.err.println();e.printStackTrace();
        }
    }
}