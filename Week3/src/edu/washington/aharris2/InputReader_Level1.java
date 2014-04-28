package edu.washington.aharris2;
import java.io.*;

/**
 * Class to read a line of text from the console and
 * print it back to the console.
 *
 * Author: Alex Harris
 * Version: 4/28/2014
 */
public class InputReader_Level1 {

    public static void main(String[] args){

        PrintStream ps = System.out;
        ps.println("Hello " + System.getProperty("user.name"));
        ps.println("Please enter a line of text.");

        InputStream in = System.in;
        InputStreamReader streamReader = new InputStreamReader(in);
        BufferedReader bufReader = new BufferedReader(streamReader);

        try {
            String line = bufReader.readLine();
            while (!line.equalsIgnoreCase("exit") && !line.equalsIgnoreCase("quit")) {
                ps.println("You said \"" + line + "\"");
                line = bufReader.readLine();
            }
        }
        catch (IOException ioe) {
            System.out.println("IO exception: " + ioe);
        }
    }
}
