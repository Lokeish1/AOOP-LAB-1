// File name: NumberPrinter.java

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NumberPrinter {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 15; i++) {
            final int number = i;
            executorService.submit(() -> {
                if (number % 2 == 0) {
                    printTwo(number);
                } else if (number % 3 == 0) {
                    printThree(number);
                } else if (number % 4 == 0) {
                    printFour(number);
                } else if (number % 5 == 0) {
                    printFive(number);
                } else {
                    printNumber(number);
                }
            });
        }

        executorService.shutdown();
    }

    public static void printTwo(int number) {
        System.out.println("Number " + number + " is divisible by 2.");
    }

    public static void printThree(int number) {
        System.out.println("Number " + number + " is divisible by 3.");
    }

    public static void printFour(int number) {
        System.out.println("Number " + number + " is divisible by 4.");
    }

    public static void printFive(int number) {
        System.out.println("Number " + number + " is divisible by 5.");
    }

    public static void printNumber(int number) {
        System.out.println("Number " + number + " is not divisible by 2, 3, 4, or 5.");
    }
}