import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class StreamPipelineExample {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.add(6);

        // Stream pipeline: filter even numbers, double them, and collect into a new list
        List<Integer> result = numbers.stream()
                                      .filter(n -> n % 2 == 0) // Filter even numbers
                                      .map(n -> n * 2)         // Double the numbers
                                      .collect(Collectors.toList()); // Collect the result into a list

        // Print the original list
        System.out.println("Original List: " + numbers);

        // Print the result list
        System.out.println("Filtered and Doubled List: " + result);
    }
}