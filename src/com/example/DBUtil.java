package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
    // Подключение к базе данных userdb
    private static final String USERDB_URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USERDB_USER = "root";
    private static final String USERDB_PASSWORD = "root";

    // Подключение к базе данных taxis
    private static final String TAXIS_URL = "jdbc:mysql://localhost:3306/taxi";
    private static final String TAXIS_USER = "root";
    private static final String TAXIS_PASSWORD = "root";

    public static Connection getUserDBConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(USERDB_URL, USERDB_USER, USERDB_PASSWORD);
    }

    public static Connection getTaxisDBConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(TAXIS_URL, TAXIS_USER, TAXIS_PASSWORD);
    }

    public static String checkUserAndGetRole(String name, String pass) {
        String query = "SELECT role FROM users WHERE name = ? AND pass = ?";
        try (Connection conn = getUserDBConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, pass);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean registerUser(String name, String pass) {
        String query = "INSERT INTO users (name, pass, role) VALUES (?, ?, 'user')";
        try (Connection conn = getUserDBConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, pass);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet getAllDrivers() throws Exception {
        String query = "SELECT * FROM drivers";
        Connection conn = getTaxisDBConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }

    public static ResultSet getAllPassengers() throws Exception {
        String query = "SELECT * FROM passengers";
        Connection conn = getTaxisDBConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }
}