package com.nordicmotorhomes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection {

    private final static String USERNAME = "paulkdb";
    private final static String PASSWORD = "sugipula@";
    private final static String CONNSTRING = "jdbc:mysql://den1.mysql1.gear.host/paulkdb"; //?useSSL=false


    static Connection getConnection() {
        try {
            System.out.println("\nConnected!\n\n");
            return DriverManager.getConnection(CONNSTRING, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
