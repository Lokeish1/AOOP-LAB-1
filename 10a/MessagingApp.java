// File name: MessagingApp.java

import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private Queue<String> buffer = new LinkedList<>();
    private final int capacity;
    
    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    // Method for Producer to add messages to the buffer
    public synchronized void produce(String message) throws InterruptedException {
        // Wait if buffer is full
        while (buffer.size() == capacity) {
            wait();
        }
        // Add message to the buffer
        buffer.add(message);
        System.out.println("Produced: " + message);
        // Notify consumer that a message is available
        notifyAll();
    }

    // Method for Consumer to consume messages from the buffer
    public synchronized String consume() throws InterruptedException {
        // Wait if buffer is empty
        while (buffer.isEmpty()) {
            wait();
        }
        // Consume message from the buffer
        String message = buffer.poll();
        System.out.println("Consumed: " + message);
        // Notify producer that space is available in the buffer
        notifyAll();
        return message;
    }
}

class Producer implements Runnable {
    private SharedBuffer sharedBuffer;

    public Producer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                String message = "Message " + i;
                sharedBuffer.produce(message);
                Thread.sleep(100); // Simulate time to generate a message
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable {
    private SharedBuffer sharedBuffer;

    public Consumer(SharedBuffer sharedBuffer) {
        this.sharedBuffer = sharedBuffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                sharedBuffer.consume();
                Thread.sleep(150); // Simulate time to process a message
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class MessagingApp {

    public static void main(String[] args) {
        // Create a shared buffer with a capacity of 5 messages
        SharedBuffer sharedBuffer = new SharedBuffer(5);

        // Create Producer and Consumer threads
        Thread producerThread = new Thread(new Producer(sharedBuffer), "Producer");
        Thread consumerThread = new Thread(new Consumer(sharedBuffer), "Consumer");

        // Start the producer and consumer threads
        producerThread.start();
        consumerThread.start();

        // Join threads to ensure they finish before the program terminates
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}