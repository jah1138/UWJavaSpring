package cp125.week4;

import java.io.*;
import java.util.Properties;

/**
 * Author: Alex
 * Version: 5/3/2014.
 */
public class BuddyProperties {

    public static void main(String[] args) throws FileNotFoundException {

        String userHome = System.getProperty("user.home");
        File dir = new File(userHome);
        System.out.println("dir = " + dir + ", userHome = " + userHome);
        File buddyFile = new File(dir, "buddies.prp");
        if (!buddyFile.isFile()) {
            throw new FileNotFoundException("FileNot Found exception: " + userHome +
                    buddyFile.getName() + " not found");
        }
        Properties p = new Properties();
        FileReader source = new FileReader(buddyFile);
        try {
            p.load(source);
            source.close();
        } catch (IOException ioe) {
            System.err.println("IO Exception: failed to load " + buddyFile.getName());
            System.err.println(ioe);
        }
        if (p.isEmpty()) {
            System.out.println("You have no buddies. :(");
        }
        int budCount = 0;
        for (String buddyID : p.stringPropertyNames()) {
            String buddyName = p.getProperty(buddyID);
            budCount++;
            System.out.println("Buddy " + budCount + ": userid = " + buddyID +
                    ", name = " + buddyName);
        }
    }
}