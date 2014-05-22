package cp125.week5.WordCounter;

import java.io.*;
import java.util.*;

/**
 * Class to manipulate text content entered by users of a chat program.
 *
 * I couldn't figure out a better way to do this. I felt like there should be
 * something very easy and I just couldn't grasp it. Capturing the words and
 * word counts is trivial, but keeping track of whose words they are added a
 * third data point, and how do you fit that in a hashmap? I tried nested maps
 * and lists, but that quickly got out of hand.
 *
 * If you have a limit on the number of chatters, you can declare as many maps
 * as you'll need, but that's not great. I wanted it to be scalable to any
 * number of chatters.
 *
 * I didn't want to look up any hints online, because I like to try to work
 * these puzzles out on my own.
 *
 * So, this is what I came up with. It works, but I'm looking forward to seeing
 * other ways to get there!  :-)
 *
 * Author: Alex
 * Version: 5/10/2014.
 */
public class  ChatWordCounter {

    private static String userHome = System.getProperty("user.home");
    private static File dir = new File(userHome);
    //private static String filename = "transcript.txt";

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(dir + "transcript.txt");
        File transcript = new File(dir, "transcript.txt");
        if (!transcript.isFile()) {
            throw new FileNotFoundException("File " + transcript.getName() + " not found");
        }
        List<String> names = new ArrayList<String>();
        HashMap<String, Integer> wordList = new HashMap<String, Integer>();
        FileReader source = new FileReader(transcript);
        BufferedReader buf = new BufferedReader(source);
        String line;
        try {
            while ((line = buf.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    String name = parts[0].trim();
                    if (!names.contains(name)) {
                        names.add(name);
                    }
                    String sentence = parts[1].trim().replaceAll("[.,:;!?/(/)]", "");
                    String[] words = sentence.split("\\s+");
                    for (String word : words) {
                        String key = name + ":" + word.trim().toLowerCase();
                        if (!wordList.containsKey(key)) {
                            wordList.put(key, 1);
                        } else {
                            wordList.put(key, wordList.get(key) + 1);
                        }
                    }
                }
            }
        }
        catch (IOException ioe) {
            //handle
        }
        /* Output section */
        Map<String, Integer> totals = new HashMap<String, Integer>();
        for (String name : names) {
            int wordCount = 0;
            totals.put(name, wordCount);
        System.out.println(name);
        for (String key : wordList.keySet()) {
            if (key.startsWith(name + ":")) {
                System.out.println(key.substring(name.length()+1) + ": - " + wordList.get(key) + " times");
            }
            else {
                continue;
            }
            wordCount += wordList.get(key);
            totals.put(name, wordCount);
//                System.out.println("Totals: Name: " + name + ", count : " + totals.get(name));
        }
        System.out.println();
        }
        System.out.println("Word counts:");
        for (String name : totals.keySet()) {
            System.out.println(name + ": " + totals.get(name) + " words");
        }
    }
}
