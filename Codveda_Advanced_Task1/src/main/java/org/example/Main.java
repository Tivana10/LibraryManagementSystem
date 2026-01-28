package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        User user = new User();
        Transaction transaction = new Transaction();

        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Add User");
            System.out.println("4. List Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Book Title: ");
                    String title = sc.nextLine();
                    System.out.print("Author: ");
                    String author = sc.nextLine();
                    book.addBook(title, author);
                    break;
                case 2:
                    book.listBooks();
                    break;
                case 3:
                    System.out.print("User Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    user.addUser(name, email);
                    break;
                case 4:
                    user.listUsers();
                    break;
                case 5:
                    System.out.print("User ID: ");
                    int userId = sc.nextInt();
                    System.out.print("Book ID: ");
                    int bookId = sc.nextInt();
                    transaction.borrowBook(userId, bookId);
                    break;
                case 6:
                    System.out.print("Transaction ID: ");
                    int transId = sc.nextInt();
                    transaction.returnBook(transId);
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
