import java.util.*;

// Task Management System using ArrayList
class TaskManagementSystem {
    private ArrayList<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        tasks.add(task);
    }

    public void updateTask(int index, String newDescription) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, newDescription);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i));
            }
        }
    }
}

// To-Do List Application using ArrayList
class ToDoList {
    private List<String> toDoList = new ArrayList<>();

    public void addTask(String task) {
        toDoList.add(task);
    }

    public void updateTask(int index, String newDescription) {
        if (index >= 0 && index < toDoList.size()) {
            toDoList.set(index, newDescription);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void removeTask(int index) {
        if (index >= 0 && index < toDoList.size()) {
            toDoList.remove(index);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void displayTasks() {
        if (toDoList.isEmpty()) {
            System.out.println("No tasks in the to-do list.");
        } else {
            for (int i = 0; i < toDoList.size(); i++) {
                System.out.println(i + ". " + toDoList.get(i));
            }
        }
    }
}

// Customer Support Ticket System using LinkedList and Queue Interface
class SupportTicketSystem {
    private Queue<String> ticketQueue = new LinkedList<>();

    public void addTicket(String ticket) {
        ticketQueue.offer(ticket);
    }

    public void processTicket() {
        String ticket = ticketQueue.poll();
        if (ticket == null) {
            System.out.println("No tickets to process.");
        } else {
            System.out.println("Processed ticket: " + ticket);
        }
    }

    public void displayTickets() {
        if (ticketQueue.isEmpty()) {
            System.out.println("No pending tickets.");
        } else {
            for (String ticket : ticketQueue) {
                System.out.println("Pending ticket: " + ticket);
            }
        }
    }
}

// Browsing History using ArrayDeque and Deque Interface
class BrowserHistory {
    private Deque<String> history = new ArrayDeque<>();
    private Deque<String> forwardStack = new ArrayDeque<>();

    public void visitPage(String page) {
        history.push(page);
        forwardStack.clear();
        System.out.println("Visited: " + page);
    }

    public void goBack() {
        if (history.size() > 1) {
            forwardStack.push(history.pop());
            System.out.println("Back to: " + history.peek());
        } else {
            System.out.println("No pages to go back to.");
        }
    }

    public void goForward() {
        if (!forwardStack.isEmpty()) {
            history.push(forwardStack.pop());
            System.out.println("Forward to: " + history.peek());
        } else {
            System.out.println("No pages to go forward to.");
        }
    }

    public void displayCurrentPage() {
        if (history.isEmpty()) {
            System.out.println("No current page.");
        } else {
            System.out.println("Current page: " + history.peek());
        }
    }
}

// Music Playlist using LinkedList
class MusicPlaylist {
    private LinkedList<String> playlist = new LinkedList<>();

    public void addSong(String song) {
        playlist.add(song);
    }

    public void removeSong(String song) {
        if (playlist.remove(song)) {
            System.out.println("Removed song: " + song);
        } else {
            System.out.println("Song not found in playlist.");
        }
    }

    public void moveSong(int oldIndex, int newIndex) {
        if (oldIndex >= 0 && oldIndex < playlist.size() && newIndex >= 0 && newIndex < playlist.size()) {
            String song = playlist.remove(oldIndex);
            playlist.add(newIndex, song);
            System.out.println("Moved song to new position.");
        } else {
            System.out.println("Invalid indices");
        }
    }

    public void displayPlaylist() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
        } else {
            for (int i = 0; i < playlist.size(); i++) {
                System.out.println(i + ". " + playlist.get(i));
            }
        }
    }
}

// Main class to test all the systems
public class TaskManagerApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Task Management System
        System.out.println("\n--- Task Management System ---");
        TaskManagementSystem taskSystem = new TaskManagementSystem();
        taskSystem.addTask("Complete homework");
        taskSystem.addTask("Read a book");
        taskSystem.displayTasks();
        taskSystem.updateTask(1, "Read a novel");
        taskSystem.displayTasks();
        taskSystem.removeTask(0);
        taskSystem.displayTasks();

        // To-Do List Application
        System.out.println("\n--- To-Do List Application ---");
        ToDoList toDoList = new ToDoList();
        toDoList.addTask("Buy groceries");
        toDoList.addTask("Call mom");
        toDoList.displayTasks();
        toDoList.updateTask(1, "Call dad");
        toDoList.displayTasks();
        toDoList.removeTask(0);
        toDoList.displayTasks();

        // Customer Support Ticket System
        System.out.println("\n--- Customer Support Ticket System ---");
        SupportTicketSystem ticketSystem = new SupportTicketSystem();
        ticketSystem.addTicket("Ticket 1: Can't log in");
        ticketSystem.addTicket("Ticket 2: Forgot password");
        ticketSystem.displayTickets();
        ticketSystem.processTicket();
        ticketSystem.displayTickets();

        // Browser History using Deque
        System.out.println("\n--- Browser History ---");
        BrowserHistory browserHistory = new BrowserHistory();
        browserHistory.visitPage("www.google.com");
        browserHistory.visitPage("www.facebook.com");
        browserHistory.goBack();
        browserHistory.goForward();
        browserHistory.displayCurrentPage();

        // Music Playlist using LinkedList
        System.out.println("\n--- Music Playlist ---");
        MusicPlaylist musicPlaylist = new MusicPlaylist();
        musicPlaylist.addSong("Song A");
        musicPlaylist.addSong("Song B");
        musicPlaylist.addSong("Song C");
        musicPlaylist.displayPlaylist();
        musicPlaylist.moveSong(0, 2);
        musicPlaylist.displayPlaylist();
        musicPlaylist.removeSong("Song B");
        musicPlaylist.displayPlaylist();

        scanner.close();
    }
}