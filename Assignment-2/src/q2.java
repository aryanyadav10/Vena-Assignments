import java.util.*;

//Problem 2: Unique Word Frequency Counter (Map, TreeSet)
//Given a paragraph, calculate and print:
//        •	Number of unique words
//•	Frequency of each word
//•	Display words sorted alphabetically


public class q2 {
    public static void main(String[] args) {
        String paragraph = "Java is a programming language. Java is also a platform. A Java program can run anywhere.";

        // Normalize the input: lowercase + remove punctuation
        paragraph = paragraph.toLowerCase().replaceAll("[^a-z ]", "");

        // Split into words
        String[] words = paragraph.split("\\s+");

        // TreeMap to store sorted unique words and their frequencies
        Map<String, Integer> wordMap = new TreeMap<>();

        for (String word : words) {
            wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
        }

        // Output: Total unique words
        System.out.println("Total number of unique words: " + wordMap.size());

        // Output: Frequency of each word in sorted order
        System.out.println("Frequency of each word:");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Output: Sorted words only
        System.out.println("Words Sorted Alphabetically: " + wordMap.keySet());
    }
}