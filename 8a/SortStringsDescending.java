import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class SortStringsDescending {
    public static void main(String[] args) {
        // Create a list of strings
        List<String> strings = new ArrayList<>();
        strings.add("Apple");
        strings.add("Banana");
        strings.add("Peach");
        strings.add("Orange");
        strings.add("Grapes");

        // Print the original list
        System.out.println("Original List:");
        strings.forEach(System.out::println);

        // Sort the list in descending order using a lambda expression
        Collections.sort(strings, (str1, str2) -> str2.compareTo(str1));

        // Print the sorted list
        System.out.println("\nList Sorted in Descending Order:");
        strings.forEach(System.out::println);
    }
}