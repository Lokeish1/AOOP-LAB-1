// Generic stack interface
interface GenericStack<T> {
    void push(T item);          // Push an item onto the stack
    T pop();                    // Pop an item from the stack
    T peek();                   // Peek at the top item without removing it
    boolean isEmpty();          // Check if the stack is empty
}

// Stack implementation using an array
class ArrayStack<T> implements GenericStack<T> {
    private T[] stack;
    private int top;
    private int capacity;

    // Constructor
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        stack = (T[]) new Object[capacity];  // Create a generic array
        top = -1;
    }

    @Override
    public void push(T item) {
        if (top == capacity - 1) {
            System.out.println("Stack is full. Cannot push.");
            return;
        }
        stack[++top] = item;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }
        return stack[top--];
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Nothing to peek.");
            return null;
        }
        return stack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}

// Stack implementation using a linked list
class LinkedListStack<T> implements GenericStack<T> {
    private Node<T> top;

    // Node class for LinkedList
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Cannot pop.");
            return null;
        }
        T item = top.data;
        top = top.next;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty. Nothing to peek.");
            return null;
        }
        return top.data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}

// Test class to test the stack implementations with different data types
public class GenericStackTest {
    public static void main(String[] args) {
        // Testing ArrayStack with Integer
        System.out.println("Testing ArrayStack with Integer:");
        GenericStack<Integer> intArrayStack = new ArrayStack<>(5);
        intArrayStack.push(10);
        intArrayStack.push(20);
        System.out.println("Peek: " + intArrayStack.peek());
        System.out.println("Pop: " + intArrayStack.pop());
        System.out.println("Is empty: " + intArrayStack.isEmpty());

        // Testing LinkedListStack with Integer
        System.out.println("\nTesting LinkedListStack with Integer:");
        GenericStack<Integer> intLinkedListStack = new LinkedListStack<>();
        intLinkedListStack.push(10);
        intLinkedListStack.push(20);
        System.out.println("Peek: " + intLinkedListStack.peek());
        System.out.println("Pop: " + intLinkedListStack.pop());
        System.out.println("Is empty: " + intLinkedListStack.isEmpty());

        // Testing ArrayStack with String
        System.out.println("\nTesting ArrayStack with String:");
        GenericStack<String> stringArrayStack = new ArrayStack<>(5);
        stringArrayStack.push("Hello");
        stringArrayStack.push("World");
        System.out.println("Peek: " + stringArrayStack.peek());
        System.out.println("Pop: " + stringArrayStack.pop());
        System.out.println("Is empty: " + stringArrayStack.isEmpty());

        // Testing LinkedListStack with Character
        System.out.println("\nTesting LinkedListStack with Character:");
        GenericStack<Character> charLinkedListStack = new LinkedListStack<>();
        charLinkedListStack.push('A');
        charLinkedListStack.push('B');
        System.out.println("Peek: " + charLinkedListStack.peek());
        System.out.println("Pop: " + charLinkedListStack.pop());
        System.out.println("Is empty: " + charLinkedListStack.isEmpty());
    }
}