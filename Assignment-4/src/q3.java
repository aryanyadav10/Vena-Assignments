import java.util.*;
import java.util.function.Consumer;

//Use a Consumer<String> to print a string in uppercase.
public class q3 {
    public static void main(String[] args){
        Consumer<String> printUpper = s -> System.out.println(s.toUpperCase());
        printUpper.accept("hellworld");
        printUpper.accept("javaworld");
    }
}
