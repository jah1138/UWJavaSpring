package edu.washington.aharris2;
import java.io.*;

/**
 * Class to read a line of text from the console, record the line number,
 * and print this information back to the console.
 *
 * Author: Alex Harris
 * Version: 4/28/2014
 */
public class InputReader_Level2 {

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
                int lineNumber = lnReader.getLineNumber();
                ps.println("Line " + lineNumber + ": you said \"" + line + "\"");
                line = lnReader.readLine();
            }
        }
        catch (IOException ioe) {
            System.out.println("IO exception: " + ioe);
        }
    }
}
