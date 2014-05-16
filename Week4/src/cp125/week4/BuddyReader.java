package cp125.week4;

import java.io.*;

/**
 * Author: Alex
 * Version: 5/3/2014.
 */
public class BuddyReader {

    public static void main(String[] args) throws FileNotFoundException {

        String userHome = System.getProperty("user.home");
        File dir = new File(userHome);
        File buddyFile = new File(dir, "buddies.prp");
        if (!buddyFile.isFile()) {
            throw new FileNotFoundException("File " + buddyFile.getName() + " not found");
        }
        FileReader source = new FileReader(buddyFile);
        BufferedReader br = new BufferedReader(source);
        int budCount = 0;
        String line;
        try {
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                budCount++;
                String[] budInfo = line.split("=");
                if (budInfo.length != 2) {
                    System.out.println("Parse error: config entry \"" + line + "\" has " +
                            "an unexpected number of tokens.");
                    continue;
                }
                System.out.print("Buddy " + budCount + ": userid = " + budInfo[0].trim() + ", ");
                System.out.println("name = " + budInfo[1].trim());
            }
            source.close();
        }
        catch(IOException ioe) {
            System.err.println("IO exception while reading config file " + buddyFile);
//            System.err.println(ioe);
            System.exit(-1);
        }
        if (budCount == 0) {
            System.out.println("You have no buddies.  :(");
        }
    }
}