package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBRepository implements IObjectRepository {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public DBRepository(){
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ArrayList<Object> readAll() {

        ArrayList<Object> objects = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM table_name");
            result = preparedStatement.executeQuery();

            while (result.next()){
                objects.add(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public Object read(int id) {

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM table_name WHERE column_name = value");
            result = preparedStatement.executeQuery();

            if (result.next()){
                return new Object();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void create(Object object) {

        try {

            preparedStatement = conn.prepareStatement("INSERT INTO table_name(column_name_1, column_name_2, column_name_3, column_name_4) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, "");
            preparedStatement.setString(2, "");
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, "");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object object) {

        try {
            preparedStatement = conn.prepareStatement("UPDATE table_name SET column_name_1 = ?, column_name_2 = ?, column_name_3 = ?, column_name_4 = ? WHERE column_name = ?");

            preparedStatement.setString(1, "");
            preparedStatement.setString(2, "");
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, "");
            preparedStatement.setString(5, "");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        try {
            preparedStatement = conn.prepareStatement("DELETE FROM table_name WHERE column_name = ?");

            preparedStatement.setString(1, "");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
