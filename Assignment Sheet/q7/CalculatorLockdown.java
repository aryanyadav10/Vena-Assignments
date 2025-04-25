package q7;

// Base class
class BasicCalculator {
    // Final method - cannot be overridden
    public final int add(int a, int b) {
        return a + b;
    }
}

// Subclass
class SmartCalculator extends BasicCalculator {
    //     @Override
    //     public int add(int a, int b) {
    //         return a + b + 10; // Not allowed - method is final
    //     }

    // New method
    public int subtract(int a, int b) {
        return a - b;
    }
}

// Main class to test
public class CalculatorLockdown {
    public static void main(String[] args) {
        SmartCalculator calc = new SmartCalculator();

        System.out.println("Addition: " + calc.add(5, 3));        // From BasicCalculator
        System.out.println("Subtraction: " + calc.subtract(5, 3)); // From SmartCalculator
    }
}

