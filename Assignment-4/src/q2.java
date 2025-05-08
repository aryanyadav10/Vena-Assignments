import java.util.*;
import java.util.function.Function;

//Create a Function<String, Integer> that returns the length of a string.

public class q2 {
    public static void main(String[] args){
        Function<String, Integer> getLength = s -> s.length();
        System.out.println(getLength.apply("HELLOWORLD"));
        System.out.println(getLength.apply("why??"));
    }
}
