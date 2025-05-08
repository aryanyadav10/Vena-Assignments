import java.util.*;

//Create a Runnable lambda that prints “Hello Functional Java”.
public class q6 {
    public static void main(String[] args) {
        Runnable greet = () -> System.out.println("Hello Functional Java");
        // Run it
        greet.run();
    }
}

