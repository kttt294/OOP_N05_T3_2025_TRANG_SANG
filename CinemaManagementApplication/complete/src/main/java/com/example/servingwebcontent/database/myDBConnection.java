package com.example.servingwebcontent.database;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.lang.Class;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Value;

@Component
public class myDBConnection {

    @Value("${my.database.url}")
    private String myDatabaseURL;

    @Value("${my.database.driver}")
    private String myDatabaseDriver;

    @Value("${my.database.username}")
    private String myDatabaseUsername;

    @Value("${my.database.password}")
    private String myDatabasePassword;

    public myDBConnection() {
        // Fallback values if properties are not loaded
        if (myDatabaseURL == null) {
            myDatabaseURL = "jdbc:mysql://avnadmin:AVNS_RE3O2bhYZ_1_6ER7YK7@mysql-14737a33-nglthu-4e05.k.aivencloud.com:17237/defaultdb?ssl-mode=REQUIRED";
        }
        if (myDatabaseDriver == null) {
            myDatabaseDriver = "com.mysql.cj.jdbc.Driver";
        }
        if (myDatabaseUsername == null) {
            myDatabaseUsername = "avnadmin";
        }
        if (myDatabasePassword == null) {
            myDatabasePassword = "AVNS_RE3O2bhYZ_1_6ER7YK7";
        }
    }

    public Statement getMyConn() {
        Connection conn = null;
        try {
            Class.forName(myDatabaseDriver);
            conn = DriverManager.getConnection(myDatabaseURL, myDatabaseUsername, myDatabasePassword);
            Statement sta = conn.createStatement();
            System.out.println("Database connection established successfully");
            return sta;
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Connection getOnlyConn() {
        try {
            Class.forName(myDatabaseDriver);
            Connection conn = DriverManager.getConnection(myDatabaseURL, myDatabaseUsername, myDatabasePassword);
            System.out.println("Database connection established successfully");
            return conn;
        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Test connection method
    public boolean testConnection() {
        try (Connection conn = getOnlyConn()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connection test successful");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Database connection test failed: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Get connection info for debugging
    public String getConnectionInfo() {
        return "URL: " + myDatabaseURL + ", Driver: " + myDatabaseDriver + ", Username: " + myDatabaseUsername;
    }
}
