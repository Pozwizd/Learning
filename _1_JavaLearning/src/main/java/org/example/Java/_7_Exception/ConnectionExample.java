package org.example.Java._7_Exception;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionExample {

    private static ConnectionExample instance;
    private Connection connection;

    private ConnectionExample() {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String username = "root";
        String password = "0000";


        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            this.connection = conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized ConnectionExample getInstance() {
        if (instance == null) {
            instance = new ConnectionExample();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }

}