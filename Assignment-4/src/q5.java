import java.util.*;
import java.util.function.BiFunction;

//Create a BiFunction<Integer, Integer, Integer> that returns the sum.
public class q5 {
    public static void main(String[] args){
        BiFunction<Integer,Integer,Integer> sum = (a,b) -> a+b;
        // Use it
        System.out.println(sum.apply(5, 3));  // Output: 8
        System.out.println(sum.apply(10, -4)); // Output: 6
    }
}
