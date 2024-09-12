import java.util.ArrayList;
import java.util.Collections;

// Generic class for a priority queue using a min-heap
class PriorityQueue<T extends Comparable<T>> {
    private ArrayList<T> heap;

    // Constructor
    public PriorityQueue() {
        heap = new ArrayList<>();
    }

    // Method to insert an element into the priority queue
    public void enqueue(T element) {
        heap.add(element);
        upHeap(heap.size() - 1);
    }

    // Method to remove and return the highest-priority element (min element)
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty.");
        }
        Collections.swap(heap, 0, heap.size() - 1);
        T minElement = heap.remove(heap.size() - 1);
        downHeap(0);
        return minElement;
    }

    // Method to get the highest-priority element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty.");
        }
        return heap.get(0);
    }

    // Method to check if the priority queue is empty
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Private method to maintain the heap structure after adding an element (up-heap)
    private void upHeap(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parentIndex)) < 0) {
                Collections.swap(heap, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Private method to maintain the heap structure after removing an element (down-heap)
    private void downHeap(int index) {
        int leftChild, rightChild, minChild;
        while (index < heap.size()) {
            leftChild = 2 * index + 1;
            rightChild = 2 * index + 2;
            minChild = index;

            if (leftChild < heap.size() && heap.get(leftChild).compareTo(heap.get(minChild)) < 0) {
                minChild = leftChild;
            }
            if (rightChild < heap.size() && heap.get(rightChild).compareTo(heap.get(minChild)) < 0) {
                minChild = rightChild;
            }

            if (minChild != index) {
                Collections.swap(heap, index, minChild);
                index = minChild;
            } else {
                break;
            }
        }
    }
}

public class GenericPriorityQueueTest {
    public static void main(String[] args) {
        // Test with Integer
        System.out.println("Testing PriorityQueue with Integers:");
        PriorityQueue<Integer> intQueue = new PriorityQueue<>();
        intQueue.enqueue(5);
        intQueue.enqueue(2);
        intQueue.enqueue(9);
        intQueue.enqueue(1);
        System.out.println("Peek: " + intQueue.peek());
        System.out.println("Dequeue: " + intQueue.dequeue());
        System.out.println("Dequeue: " + intQueue.dequeue());

        // Test with Double
        System.out.println("\nTesting PriorityQueue with Doubles:");
        PriorityQueue<Double> doubleQueue = new PriorityQueue<>();
        doubleQueue.enqueue(4.5);
        doubleQueue.enqueue(3.2);
        doubleQueue.enqueue(7.8);
        doubleQueue.enqueue(1.9);
        System.out.println("Peek: " + doubleQueue.peek());
        System.out.println("Dequeue: " + doubleQueue.dequeue());
        System.out.println("Dequeue: " + doubleQueue.dequeue());

        // Test with String
        System.out.println("\nTesting PriorityQueue with Strings:");
        PriorityQueue<String> stringQueue = new PriorityQueue<>();
        stringQueue.enqueue("Apple");
        stringQueue.enqueue("Banana");
        stringQueue.enqueue("Peach");
        stringQueue.enqueue("Grapes");
        System.out.println("Peek: " + stringQueue.peek());
        System.out.println("Dequeue: " + stringQueue.dequeue());
        System.out.println("Dequeue: " + stringQueue.dequeue());
    }
}