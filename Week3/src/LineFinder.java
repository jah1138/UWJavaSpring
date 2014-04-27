import java.io.*;

/**
 * Created by Alex on 4/23/14.
 */
public class LineFinder {

    public static void main(String[] args) {

        File f = new File("c:\\IdeaProjects\\week3\\numbers.txt");
        String line;
        try {
            System.out.println("File size = " + f.length());
            FileReader source = new FileReader(f);
            LineNumberReader reader = new LineNumberReader(source);
            while ((line = reader.readLine()) != null) {
                line = reader.readLine();
                String[] tokens = line.split(",");
                if (tokens.length == 1) {
                    System.out.println("Line = " + reader.getLineNumber());
                }
            }
        }
        catch(FileNotFoundException fnfe) {
            //recovery stuff
        }
        catch(IOException ioe) {
            // recovery stuff
        }

    }
}
