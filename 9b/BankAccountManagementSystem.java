// File name: BankAccountManagementSystem.java

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Thread-safe deposit method
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + " deposited: $" + amount + ", Current Balance: $" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to deposit invalid amount: $" + amount);
        }
    }

    // Thread-safe withdraw method
    public synchronized void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " withdrew: $" + amount + ", Current Balance: $" + balance);
        } else if (amount > balance) {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw: $" + amount + ", but insufficient funds. Current Balance: $" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " tried to withdraw invalid amount: $" + amount);
        }
    }

    // Thread-safe method to get balance
    public synchronized double getBalance() {
        return balance;
    }
}

class UserTransaction implements Runnable {
    private final BankAccount account;
    private final boolean isDeposit; // true for deposit, false for withdrawal
    private final double amount;

    public UserTransaction(BankAccount account, boolean isDeposit, double amount) {
        this.account = account;
        this.isDeposit = isDeposit;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (isDeposit) {
            account.deposit(amount);
        } else {
            account.withdraw(amount);
        }
    }
}

public class BankAccountManagementSystem {

    public static void main(String[] args) {
        // Create a shared bank account with an initial balance of $1000
        BankAccount sharedAccount = new BankAccount(1000.0);

        // Create multiple threads simulating users performing transactions
        Thread user1 = new Thread(new UserTransaction(sharedAccount, true, 200), "User 1");
        Thread user2 = new Thread(new UserTransaction(sharedAccount, false, 150), "User 2");
        Thread user3 = new Thread(new UserTransaction(sharedAccount, true, 300), "User 3");
        Thread user4 = new Thread(new UserTransaction(sharedAccount, false, 500), "User 4");
        Thread user5 = new Thread(new UserTransaction(sharedAccount, false, 1200), "User 5"); // Insufficient funds test

        // Start the transactions concurrently
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();

        // Join threads to ensure they complete before the program ends
        try {
            user1.join();
            user2.join();
            user3.join();
            user4.join();
            user5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final account balance after all transactions
        System.out.println("Final Account Balance: $" + sharedAccount.getBalance());
    }
}