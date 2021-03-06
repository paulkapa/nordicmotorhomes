package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository implements IObjectRepository<User> {

    private Connection conn = DBConnection.getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public UserRepository(){
    }

    @Override
    public ArrayList<User> readAll(String tableName) {
        ArrayList<User> users = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM users");
            result = preparedStatement.executeQuery();

            while (result.next()){
                users.add(new User(result.getInt("pKey_userId"), result.getString("fullName"), result.getString("cprNr")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean readOne(String tableName, String username, String password) {

        boolean bool = false;

        try {

            preparedStatement = conn.prepareStatement("select * from users");
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
    public User readId(int id) {

        User user = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE pKey_userId= '" + id + "'");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                user = new User(result.getInt("pKey_userId"), result.getString("fullName"), result.getString("cprNr"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public void create(String tableName, User object) {

        try {

            preparedStatement = conn.prepareStatement("INSERT INTO users(fullName, cprNr) VALUES (?, ?)");

            preparedStatement.setString(1, object.getFullName());
            preparedStatement.setString(2, object.getCprNr());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String tableName, User object) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE users SET fullName = ?, cprNr = ? WHERE pKey_userId = ?");

            preparedStatement.setString(1, object.getFullName());
            preparedStatement.setString(2, object.getCprNr());
            preparedStatement.setInt(3, object.getId());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }

}
