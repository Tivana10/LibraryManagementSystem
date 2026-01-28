package org.example;
import java.sql.*;
import java.util.Scanner;

public class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;

    // Add a new book
    public void addBook(String title, String author) {
        String sql = "INSERT INTO books(title, author, available) VALUES(?, ?, TRUE)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException ex) {
            System.out.println("Error adding book: " + ex.getMessage());
        }
    }

    // List all books
    public void listBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID | Title | Author | Available");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %b\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available"));
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching books: " + ex.getMessage());
        }
    }

    // Update book availability
    public void updateAvailability(int bookId, boolean available) {
        String sql = "UPDATE books SET available = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, available);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating book: " + ex.getMessage());
        }
    }
}
