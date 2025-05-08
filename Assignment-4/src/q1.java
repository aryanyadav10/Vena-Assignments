import java.util.*;
import java.util.function.Predicate;

//Write a Predicate<String> that returns true if a string starts with “A”.
public class q1 {
    public static void main(String[] args){
        Predicate<String> startsWithA = s -> s.startsWith("A");
        // Test it
        System.out.println(startsWithA.test("Apple"));  // true
        System.out.println(startsWithA.test("Banana")); // false
    }
}
