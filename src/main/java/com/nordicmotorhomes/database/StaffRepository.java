package com.nordicmotorhomes.database;

import com.nordicmotorhomes.models.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffRepository implements IObjectRepository<Staff> {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public StaffRepository(){
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ArrayList<Staff> readAll(String tableName) {
        ArrayList<Staff> staff = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM staff");
            result = preparedStatement.executeQuery();

            while (result.next()){
                staff.add(new Staff(result.getString("fullName"), result.getString("function"), result.getString("username"), result.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public Staff read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public boolean readOne(String username, String password) {

        boolean bool = false;

        try {

            preparedStatement = conn.prepareStatement("select * from staff");
            result = preparedStatement.executeQuery();

            while (result.next() && !bool) {

                if(result.getString("username").equals(username) && result.getString("password").equals(password)) {
                    bool = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bool;
    }

    @Override
    public void create(String tableName, Staff object) {

    }

    @Override
    public void update(String tableName, Staff object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
