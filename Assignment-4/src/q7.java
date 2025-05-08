import java.util.*;
//Use a lambda expression to sort a list of strings alphabetically.
public class q7 {
    public static void main(String[] args){
        List<String> names = Arrays.asList("apple","banana","mango","cherry");
        names.sort((s1,s2) -> s1.compareTo(s2));
        // Print sorted list
        System.out.println(names);
    }
}
