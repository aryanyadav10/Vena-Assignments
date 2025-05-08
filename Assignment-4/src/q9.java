// File: q9.java

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

//Level 3 – Collectors, Optional, Advanced Streams
//Group a list of names by their first letter using Collectors.groupingBy().
//Partition a list of integers into even and odd using Collectors.partitioningBy().
//Join a list of strings into a single comma-separated string.
//Find the first element in a list of integers that is divisible by 5 using findFirst().
//Use Optional to safely get a value or return a default.
//Convert a list of integers to a Set using Collectors.toSet().

public class q9 {
    public static void main(String[] args) {

        // 1. Group a list of names by their first letter
        List<String> names = Arrays.asList("Alice", "Bob", "Angela", "Brian", "Charlie");
        Map<Character, List<String>> groupedByFirstLetter = names.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println("Grouped by first letter: " + groupedByFirstLetter);

        // 2. Partition a list of integers into even and odd
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<Boolean, List<Integer>> evenOddPartition = numbers.stream()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println("Partitioned even/odd: " + evenOddPartition);

        // 3. Join a list of strings into a single comma-separated string
        List<String> words = Arrays.asList("Java", "Streams", "Are", "Powerful");
        String joined = words.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Joined string: " + joined);

        // 4. Find the first element in a list of integers divisible by 5 using findFirst()
        Optional<Integer> firstDivByFive = numbers.stream()
                .filter(num -> num % 5 == 0)
                .findFirst();
        System.out.println("First divisible by 5: " + firstDivByFive.orElse(-1));

        // 5. Use Optional to safely get a value or return a default
        Optional<String> maybeName = Optional.ofNullable(null); // could also be Optional.of("value")
        String safeName = maybeName.orElse("DefaultName");
        System.out.println("Optional with default: " + safeName);

        // 6. Convert a list of integers to a Set using Collectors.toSet()
        List<Integer> duplicateList = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        Set<Integer> uniqueSet = duplicateList.stream()
                .collect(Collectors.toSet());
        System.out.println("Converted to Set: " + uniqueSet);
    }
}
