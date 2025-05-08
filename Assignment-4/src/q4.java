import java.util.function.Supplier;

//Write a Supplier<Double> that generates a random number.
public class q4 {
    public static void main(String[] args) {
        Supplier<Double> val = () -> Math.random();
        System.out.println(val.get());
        System.out.println(val.get());
    }
}

