package cp125.week4;

import java.io.*;
import java.util.Properties;

/**
 * Class for managing a basic set of personal contacts stored in ID & name key-value pairs.
 *
 * Author: Alex Harris
 * Version: 5/3/2014.
 */
public class BuddyStorer {

    /**
     * Variables accessible to class methods
     */
    private static String userHome = System.getProperty("user.home");
    private static File dir = new File(userHome);
    private static File buddyFile;
    private static String fileName = "buddies.prp";
    static Properties p = new Properties();

    /**
     * Main method checks for file existence and calls subsidiary methods.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            fileName = args[0];
        }
        try {
            buddyFile = new File(dir, fileName);
            p = loadBuddyList(buddyFile);
        }
        catch(FileNotFoundException fnf) {
            System.err.println(buddyFile.getName() + " not found");
        }
        /* This section is for meeting the terms of the assignment. Real testing
        should be done in a test class. */
        String buddyID = "pele", buddyName = "Edson Arantes di Nascimento";
        addBuddy(buddyID, buddyName);
        saveBuddyList();
    }

    /**
     * Loads the contacts file data into a Properties object.
     *
     * @param buddyFile contact file.
     * @return Properties object containing contacts in ID/Name K/V pairs.
     * @throws FileNotFoundException
     */
    public static Properties loadBuddyList(File buddyFile) throws FileNotFoundException {
        FileReader source = new FileReader(buddyFile);
        try {
            p.load(source);
            source.close();
        } catch (IOException ioe) {
            System.err.println("IO Exception: failed to load " + buddyFile.getName());
        }
        return p;
    }

    /**
     * Adds a provided user ID and name K/V pair to the Properties object.
     * Prevents duplicate keys and values.
     *
     * @param userid the contact's ID.
     * @param name the contact's full name.
     */
    public static void addBuddy(String userid, String name) {
        Boolean unique = true;
        if (p.containsKey(userid)) {
            System.out.println("UserID \"" + userid + "\" is already in use.");
            unique = false;
        }
        if (p.containsValue(name)) {
            System.out.println("Username \"" + name + "\" is already in use.");
            unique = false;
        }
        if (unique) {
            p.put(userid, name);
        }
    }

    /**
     * Prints the list of contacts.
     * Displays contact IDs and names. The contact rows are numbered, but the numbers
     * are for viewing convenience and are not associated with a specific contact.
     */
    public static void printBuddyList() {
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

    /**
     * Writes the contact list back to the data file for persistence.
     */
    public static void saveBuddyList() {
        try {
            PrintWriter pw = new PrintWriter(buddyFile);
            p.store(pw, "Contacts updated");
            pw.close();
        }
        catch(IOException ioe) {
            System.err.println("IOE in method saveBuddyList, file " + buddyFile.getName());
        }
    }
}