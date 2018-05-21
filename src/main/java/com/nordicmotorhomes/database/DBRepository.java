package com.nordicmotorhomes.database;

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
    public ArrayList<Object> readAll(String tableName) {

        ArrayList<Object> objects = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM '"+ tableName +"'");
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
    public Object read(String tableName, String columnName, String value) {

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM '"+ tableName +"' WHERE '"+ columnName +"' = '"+ value +"'");
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
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public Object readId(int id) {
        return null;
    }

    @Override
    public void create(String tableName, Object object) {

        try {

            preparedStatement = conn.prepareStatement("INSERT INTO '"+ tableName +"'(column_name_1, column_name_2, column_name_3, column_name_4) VALUES (?, ?, ?, ?)");

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
    public void update(String tableName, Object object) {

        try {
            preparedStatement = conn.prepareStatement("UPDATE '"+ tableName +"' SET column_name_1 = ?, column_name_2 = ?, column_name_3 = ?, column_name_4 = ? WHERE column_name = ?");

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
    public void delete(String tableName, String columnName, String value) {

        try {
            preparedStatement = conn.prepareStatement("DELETE FROM '"+ tableName +"' WHERE '"+ columnName +"' = '"+ value +"'");

            //preparedStatement.setString(1, "");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
