package org.example;
import java.sql.*;

public class User {
    private int id;
    private String name;
    private String email;

    // Add a new user
    public void addUser(String name, String email) {
        String sql = "INSERT INTO users(name, email) VALUES(?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLException ex) {
            System.out.println("Error adding user: " + ex.getMessage());
        }
    }

    // List all users
    public void listUsers() {
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("ID | Name | Email");
            while (rs.next()) {
                System.out.printf("%d | %s | %s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.out.println("Error fetching users: " + ex.getMessage());
        }
    }
}
