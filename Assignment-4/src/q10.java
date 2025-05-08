import java.util.*;
import java.util.function.*;
import java.util.stream.*;

//Level 4 – Complex Operations and Composition
//Write a function that takes a list of strings and returns a map of word to its length.
//Compose two functions: one that converts string to uppercase and one that appends “!”.
//Chain multiple stream operations: filter > map > sort > collect.
//Given a list of people (name, age), filter adults (age > 18) and collect names.
//From a list of words, remove duplicates and return sorted result.
//Compute the average length of strings in a list.
//Find the product of all even numbers in a list using reduce().

class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

public class q10 {
    // 1. Function that takes list of strings and returns map of word -> length
    public static Map<String, Integer> wordLengthMap(List<String> words) {
        return words.stream()
                .collect(Collectors.toMap(word -> word, word -> word.length(), (a, b) -> a));
    }

    public static void main(String[] args) {

        // Sample data
        List<String> words = Arrays.asList("apple", "banana", "apple", "cherry", "date");

        // 1. Map of word to its length
        Map<String, Integer> map = wordLengthMap(words);
        System.out.println("Word to length map: " + map);

        // 2. Compose two functions: uppercase and append "!"
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> addExclamation = str -> str + "!";
        Function<String, String> composed = toUpperCase.andThen(addExclamation);
        System.out.println("Composed Function Output: " + composed.apply("hello"));

        // 3. Chain filter > map > sort > collect
        List<String> filteredSorted = words.stream()
                .filter(w -> w.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Filtered, mapped, sorted: " + filteredSorted);

        // 4. Filter adults and collect names
        List<Person> people = Arrays.asList(
                new Person("Alice", 17),
                new Person("Bob", 22),
                new Person("Charlie", 19),
                new Person("David", 15)
        );

        List<String> adultNames = people.stream()
                .filter(p -> p.age > 18)
                .map(p -> p.name)
                .collect(Collectors.toList());
        System.out.println("Adult names: " + adultNames);

        // 5. Remove duplicates and return sorted result
        List<String> uniqueSorted = words.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Unique & Sorted words: " + uniqueSorted);

        // 6. Compute average length of strings
        OptionalDouble avgLength = words.stream()
                .mapToInt(String::length)
                .average();
        System.out.println("Average length: " + avgLength.orElse(0));

        // 7. Product of all even numbers using reduce
        List<Integer> nums = Arrays.asList(2, 3, 4, 6, 7, 8);
        int product = nums.stream()
                .filter(n -> n % 2 == 0)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Product of even numbers: " + product);
    }
}
