package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final static String USERNAME = "paulkdb";
    private final static String PASSWORD = "sugipula@";
    private final static String CONNSTRING = "jdbc:mysql://den1.mysql1.gear.host/paulkdb"; //?useSSL=false


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(CONNSTRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
