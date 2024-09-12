import java.util.Arrays;
import java.util.Comparator;

// Generic interface to find Max and Min
interface ArrayOperations<T> {
    T findMax(T[] array);
    T findMin(T[] array);
}

// Generic class that implements the ArrayOperations interface
class ArrayProcessor<T extends Comparable<T>> implements ArrayOperations<T> {
    
    // Method to find maximum value
    @Override
    public T findMax(T[] array) {
        return Arrays.stream(array).max(Comparator.naturalOrder()).orElse(null);
    }
    
    // Method to find minimum value
    @Override
    public T findMin(T[] array) {
        return Arrays.stream(array).min(Comparator.naturalOrder()).orElse(null);
    }
}

public class GenericArrayOperations {
    public static void main(String[] args) {
        // Example with Integer
        Integer[] intArray = {5, 3, 9, 1, 6};
        ArrayProcessor<Integer> intProcessor = new ArrayProcessor<>();
        System.out.println("Integer Array: " + Arrays.toString(intArray));
        System.out.println("Max Integer: " + intProcessor.findMax(intArray));
        System.out.println("Min Integer: " + intProcessor.findMin(intArray));

        // Example with String
        String[] strArray = {"Apple", "Banana", "Peach", "Grapes"};
        ArrayProcessor<String> strProcessor = new ArrayProcessor<>();
        System.out.println("\nString Array: " + Arrays.toString(strArray));
        System.out.println("Max String: " + strProcessor.findMax(strArray));
        System.out.println("Min String: " + strProcessor.findMin(strArray));

        // Example with Character
        Character[] charArray = {'a', 'd', 'b', 'z', 'x'};
        ArrayProcessor<Character> charProcessor = new ArrayProcessor<>();
        System.out.println("\nCharacter Array: " + Arrays.toString(charArray));
        System.out.println("Max Character: " + charProcessor.findMax(charArray));
        System.out.println("Min Character: " + charProcessor.findMin(charArray));

        // Example with Float
        Float[] floatArray = {5.5f, 3.3f, 9.9f, 1.1f, 6.6f};
        ArrayProcessor<Float> floatProcessor = new ArrayProcessor<>();
        System.out.println("\nFloat Array: " + Arrays.toString(floatArray));
        System.out.println("Max Float: " + floatProcessor.findMax(floatArray));
        System.out.println("Min Float: " + floatProcessor.findMin(floatArray));
    }
}