import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Book {
    int id;
    String title;
    String author;
    boolean isIssued;
    LocalDate dueDate;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.dueDate = null;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + title + " by " + author + " | Status: " + (isIssued ? "Issued" : "Available");
    }
}

class User {
    int userId;
    String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}

public class LibraryApp {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public LibraryApp() {
        // Pre-seed some dummy data
        books.add(new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(2, "1984", "George Orwell"));
        books.add(new Book(3, "To Kill a Mockingbird", "Harper Lee"));
        users.add(new User(101, "Alice"));
    }

    public void displayBooks() {
        System.out.println("\n--- Library Catalog ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = scanner.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                if (b.isIssued) {
                    System.out.println("Book is already issued to someone else.");
                    return;
                }
                b.isIssued = true;
                // Issue for 14 days
                b.dueDate = LocalDate.now().plusDays(14);
                
                // Demo override: Let's pretend it was issued 20 days ago so due date has passed
                // Un-comment to test fine calculations:
                // b.dueDate = LocalDate.now().minusDays(6); 
                
                System.out.println("Success! Book issued. Due date is: " + b.dueDate);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();
        for (Book b : books) {
            if (b.id == id) {
                if (!b.isIssued) {
                    System.out.println("This book is not currently issued.");
                    return;
                }
                
                b.isIssued = false;
                LocalDate today = LocalDate.now();
                long overdueDays = ChronoUnit.DAYS.between(b.dueDate, today);
                
                System.out.println("Book returned successfully.");
                if (overdueDays > 0) {
                    // Fine logic: $2 per day overdue
                    long fine = overdueDays * 2;
                    System.out.println("Notice: Book is overdue by " + overdueDays + " days.");
                    System.out.println("Total Fine Due: $" + fine);
                } else {
                    System.out.println("Returned on time. No fines!");
                }
                
                b.dueDate = null;
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void run() {
        System.out.println("Welcome to the Java Library Management System");
        while (true) {
            System.out.println("\n1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book (and calculate fines)");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) displayBooks();
            else if (choice == 2) issueBook();
            else if (choice == 3) returnBook();
            else if (choice == 4) break;
            else System.out.println("Invalid choice.");
        }
    }

    public static void main(String[] args) {
        LibraryApp app = new LibraryApp();
        app.run();
    }
}
