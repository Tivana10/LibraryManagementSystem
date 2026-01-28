package org.example;

import java.sql.*;
import java.time.LocalDate;

public class Transaction {

    // Borrow a book
    public void borrowBook(int userId, int bookId) {
        String checkSql = "SELECT available FROM books WHERE id = ?";
        String borrowSql = "INSERT INTO transactions(user_id, book_id, borrow_date) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("available")) {
                PreparedStatement borrowStmt = conn.prepareStatement(borrowSql);
                borrowStmt.setInt(1, userId);
                borrowStmt.setInt(2, bookId);
                borrowStmt.setDate(3, Date.valueOf(LocalDate.now()));
                borrowStmt.executeUpdate();

                // Mark book as unavailable
                String updateBook = "UPDATE books SET available = FALSE WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateBook);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();

                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book is not available.");
            }
        } catch (SQLException ex) {
            System.out.println("Error borrowing book: " + ex.getMessage());
        }
    }

    // Return a book
    public void returnBook(int transactionId) {
        String sql = "UPDATE transactions SET return_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, transactionId);
            stmt.executeUpdate();

            // Make book available again
            String bookSql = "SELECT book_id FROM transactions WHERE id = ?";
            PreparedStatement bookStmt = conn.prepareStatement(bookSql);
            bookStmt.setInt(1, transactionId);
            ResultSet rs = bookStmt.executeQuery();
            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                String updateBook = "UPDATE books SET available = TRUE WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateBook);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
            }

            System.out.println("Book returned successfully!");
        } catch (SQLException ex) {
            System.out.println("Error returning book: " + ex.getMessage());
        }
    }
}
