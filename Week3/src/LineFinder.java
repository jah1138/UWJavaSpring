import java.io.*;

/**
 * Program written in class to check the file of CS number pairs created by Stuart and find
 * the line where there's only a single number instead of a pair of number.
 *
 * Created by Alex on 4/23/14.
 */
public class LineFinder {

    public static void main(String[] args) {

        File f = new File("c:\\IdeaProjects\\UWJavaSpring\\week3\\numbers.txt");
        String line;
        try {
            System.out.println("File size: " + f.length() + " bytes");
            FileReader source = new FileReader(f);
            LineNumberReader reader = new LineNumberReader(source);
            while ((line = reader.readLine()) != null) {
                line = reader.readLine();
                String[] tokens = line.split(",");
                if (tokens.length == 1) {
                    System.out.println("Unique line location: " + reader.getLineNumber());
                }
            }
        }
        catch(FileNotFoundException fnfe) {
            System.out.println("File not found: " + f.getName());
        }
        catch(IOException ioe) {
            System.out.println("IO Exception here buddy.");
        }

    }
}
