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
public class ChatWordCounterSorted {

    private static String userHome = System.getProperty("user.home");
    private static String filename = "\\transcript.txt";
    private static File transcript = null;
    private static List<String> chatNames = null;

    public static void main(String[] args) {
        if (args.length > 0) {
            filename = args[0];
        }
        File dir = new File(userHome);
        transcript = new File(dir, filename); // "transcript.txt");
        Map<String, Integer> wordMap = processChatWordData(transcript);
        //printChatWordData(wordMap);
        printChatWordData(sortValues(wordMap));
        }

    private static Map<String, Integer> processChatWordData(File file) {
        chatNames = new ArrayList<String>();
        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            FileReader source = new FileReader(transcript);
            BufferedReader buf = new BufferedReader(source);
            String line;
            while ((line = buf.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    String name = parts[0].trim();
                    if (!chatNames.contains(name)) {
                        chatNames.add(name);
                    }
                    String sentence = parts[1].trim().replaceAll("[.,:;!?/(/)]", "");
                    String[] words = sentence.split("\\s+");
                    for (String word : words) {
                        String key = name + ":" + word.trim().toLowerCase();
                        if (!wordMap.containsKey(key)) {
                            wordMap.put(key, 1);
                        } else {
                            wordMap.put(key, wordMap.get(key) + 1);
                        }
                    }
                }
            }
        }
        catch (FileNotFoundException fnf) {
            System.err.println("File not found");
        }
        catch (IOException ioe) {
            System.err.println("IO exception");
        }
        return wordMap;
    }

    private static void printChatWordData(Map<String, Integer> map) {
        Map<String, Integer> totals = new HashMap<String, Integer>();
        for (String name : chatNames) {
            int wordCount = 0;
            totals.put(name, wordCount);
            System.out.println(name + ":");
            for (String key : map.keySet()) {
                if (key.startsWith(name + ":")) {
                    System.out.println(key.substring(name.length() + 1) + " - " + map.get(key) + " times");
                } else {
                    continue;
                }
                wordCount += map.get(key);
                totals.put(name, wordCount);
//                System.out.println("Totals: Name: " + name + ", count : " + totals.get(name));
            }
            System.out.println();
        }
//        System.out.println("Word counts:");
        for (String name : totals.keySet()) {
            System.out.println(name + ": " + totals.get(name) + " words");
        }
        System.out.println();
    }

    private static Map<String, Integer> sortValues(Map<String, Integer> map) {
        List<String> keys = new LinkedList<String>();
        keys.addAll(map.keySet());
        Collections.sort(keys);
        Map<String,Integer> sortedWordMap = new LinkedHashMap<String, Integer>();
        for (String key : keys) {
            if (map.containsKey(key)) {
                sortedWordMap.put(key, map.get(key)) ;
            }
        }
        return sortedWordMap;
    }
}
