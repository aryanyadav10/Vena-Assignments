import java.util.*;
public class q2 {
    public static void main(String[] args){
        String paragraph = "Java is a programming language. Java is also a platform. A Java program can run anywhere.";
        paragraph = paragraph.toLowerCase();
        String[] words = paragraph.split(" ");

        Set<String> st = new HashSet<>();
        Map<String,Integer> m = new HashMap<>();
        Set<String> sorted = new TreeSet<>();

        for(String word : words){
            st.add(word);
            m.put(word,m.getOrDefault(word,0)+1);
            sorted.add(word);
        }

        System.out.println("Total number of unique words: " + st.size());
        System.out.println("Frequency of each word: ");
        for(Map.Entry<String,Integer> entry : m.entrySet()){
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
        System.out.println("Words Sorted Alphabetically: " + sorted);
    }
}