package org.example;
public class TestConnection {
    public static void main(String[] args) {
        try {
            DatabaseConnection.getConnection();
            System.out.println("✅ MySQL connected!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
