package com.gym.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseConnection {
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (InputStream input = DatabaseConnection.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {
                Properties prop = new Properties();
                prop.load(input);
                connection = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.user"),
                    prop.getProperty("db.password"));
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLException("Failed to connect to database");
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}