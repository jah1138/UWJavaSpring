package edu.washington.aharris2;
import java.io.*;

/**
 * Class to read a line of text from the console, record the line number and
 * number of words, and print this information back to the console.
 *
 * Author: Alex Harris
 * Version: 4/28/2014
 */
public class InputReader_Level3 {

    public static void main(String[] args){

        PrintStream ps = System.out;
        ps.println("Hello " + System.getProperty("user.name"));
        ps.println("Please enter a line of text.");

        InputStream in = System.in;
        InputStreamReader strReader = new InputStreamReader(in);
        LineNumberReader lnReader = new LineNumberReader(strReader);

        try {
            String line = lnReader.readLine();
            while (!line.equalsIgnoreCase("exit") && !line.equalsIgnoreCase("quit")) {
                String[] words = line.trim().split("[,.:;\\s]+");
                int lineNumber = lnReader.getLineNumber();
                int numWords = words.length;
                ps.println("Line " + lineNumber + ", " + numWords + " words: you said \"" + line + "\"");
                line = lnReader.readLine();
            }
        }
        catch (IOException ioe) {
            System.out.println("IO exception: " + ioe);
        }
    }
}
