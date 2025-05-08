import java.util.*;
import java.util.stream.Collectors;

//Filter a list of integers to only even numbers.
//Convert a list of strings to uppercase using map().
//Given a list of names, return a list of names longer than 4 characters.
//Count how many strings in a list start with “S”.
//Find the longest string in a list using reduce().
//Sort a list of integers in descending order using streams.
//Convert a list of integers into a list of their squares.
//From a list of words, create a list of their lengths.
import java.util.*;
import java.util.stream.*;

public class q8 {
    public static void main(String[] args) {
        // Sample data
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 10);
        List<String> strList = Arrays.asList("Sun", "Sky", "Sea", "Stone", "Spark", "Ocean", "Air");

        // 1. Filter a list of integers to only even numbers
        List<Integer> evenNumbers = intList.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even Numbers: " + evenNumbers);

        // 2. Convert a list of strings to uppercase using map()
        List<String> upperStrings = strList.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase Strings: " + upperStrings);

        // 3. Given a list of names, return a list of names longer than 4 characters
        List<String> longNames = strList.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Names longer than 4 chars: " + longNames);

        // 4. Count how many strings in a list start with “S”
        long countStartsWithS = strList.stream()
                .filter(s -> s.startsWith("S"))
                .count();
        System.out.println("Count of strings starting with 'S': " + countStartsWithS);

        // 5. Find the longest string in a list using reduce()
        Optional<String> longest = strList.stream()
                .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2);
        System.out.println("Longest string: " + longest.orElse("None"));

        // 6. Sort a list of integers in descending order using streams
        List<Integer> sortedDescending = intList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Sorted Descending: " + sortedDescending);

        // 7. Convert a list of integers into a list of their squares
        List<Integer> squaredList = intList.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Squares: " + squaredList);

        // 8. From a list of words, create a list of their lengths
        List<Integer> lengths = strList.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Lengths of words: " + lengths);
    }
}

