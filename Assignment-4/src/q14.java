import java.util.*;
import java.util.function.*;
import java.util.stream.*;

//Function Composition & Pipelines
//Build a reusable pipeline of 3 functions: trim, lowercase, and remove punctuation from strings.
//Compose two functions: one for string parsing (String -> Integer) and one for doubling the number (Integer -> Integer).
//Write a generic method that accepts a List<T> and a Function<T, R> and applies the function to all elements.
//Implement compose() and andThen() examples using Function<T, R>.

public class q14 {

    // 1. Build a reusable pipeline of 3 functions: trim, lowercase, and remove punctuation from strings.
    public static String processString(String input) {
        Function<String, String> trimFunction = str -> str.trim();
        Function<String, String> lowercaseFunction = str -> str.toLowerCase();
        Function<String, String> removePunctuationFunction = str -> str.replaceAll("[^a-zA-Z0-9 ]", "");

        // Compose functions: trim > lowercase > remove punctuation
        return trimFunction.andThen(lowercaseFunction).andThen(removePunctuationFunction).apply(input);
    }

    // 2. Compose two functions: one for string parsing (String -> Integer) and one for doubling the number (Integer -> Integer).
    public static Function<String, Integer> parseAndDouble = str -> {
        Function<String, Integer> parseToInt = Integer::parseInt;
        Function<Integer, Integer> doubleNumber = x -> x * 2;

        return parseToInt.andThen(doubleNumber).apply(str);
    };

    // 3. Write a generic method that accepts a List<T> and a Function<T, R> and applies the function to all elements.
    public static <T, R> List<R> applyFunctionToList(List<T> list, Function<T, R> function) {
        return list.stream()
                .map(function)
                .collect(Collectors.toList());
    }

    // 4. Implement compose() and andThen() examples using Function<T, R>.
    public static void functionCompositionExample() {
        Function<String, String> addPrefix = str -> "Hello, " + str;
        Function<String, String> toUpperCase = str -> str.toUpperCase();

        // Using compose() - applies toUpperCase first, then addPrefix
        Function<String, String> greetAndUppercase = addPrefix.compose(toUpperCase);

        // Using andThen() - applies addPrefix first, then toUpperCase
        Function<String, String> greetAndLowercase = addPrefix.andThen(toUpperCase);

        String name = "john";
        System.out.println("Using compose(): " + greetAndUppercase.apply(name)); // Output: HELLO, JOHN
        System.out.println("Using andThen(): " + greetAndLowercase.apply(name)); // Output: HELLO, JOHN
    }

    public static void main(String[] args) {
        // 1. Test the pipeline
        String input = "   Hello, World!   ";
        System.out.println("Processed string: " + processString(input));

        // 2. Test parse and double function
        String numberString = "5";
        System.out.println("Parsed and doubled: " + parseAndDouble.apply(numberString));

        // 3. Test applying a function to a list
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> processedNames = applyFunctionToList(names, name -> "Hello, " + name);
        System.out.println("Processed Names: " + processedNames);

        // 4. Test compose() and andThen() examples
        functionCompositionExample();
    }
}
