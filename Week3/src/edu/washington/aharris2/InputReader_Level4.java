package edu.washington.aharris2;
import java.io.*;

/**
 * Class to read a line of text from the console, record the line number and
 * number of words, and print this information back to the console.
 *
 * Author: Alex Harris
 * Version: 4/28/2014
 */
public class InputReader_Level4 {

    public static void main(String[] args) {

        PrintStream ps = System.out;
        String user = System.getProperty("user.name");

        InputStream in = System.in;
        InputStreamReader strReader = new InputStreamReader(in);
        LineNumberReader lnReader = new LineNumberReader(strReader);

        try {
            String line = lnReader.readLine();
            int longest = 0;
            String longWord = null;
            while (!line.equalsIgnoreCase("exit") && !line.equalsIgnoreCase("quit")) {
                ps.print("Hello " + user + ": ");
                int lineNumber = lnReader.getLineNumber();
                String[] words = line.trim().split("[,.:;\\s]+");
                int numWords = words.length;
                for (String word : words) {
                    if (word.length() > longest) {
                        longest = word.length();
                        longWord = word;
                    }
                }
                ps.println("Line " + lineNumber + ", " + numWords + " words: you said \"" + line + "\"");
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
        try {
            in.close();
        } catch (IOException e) {
            System.err.println("Error closing InputStream.");
            System.err.println();e.printStackTrace();
        }
    }
}