package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Type;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TypeRepository implements IObjectRepository<Type> {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public TypeRepository(){
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ArrayList<Type> readAll(String tableName) {
        return null;
    }

    @Override
    public Type read(String tableName, String columnName, String value) {
        Type type = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM types WHERE pKey_typeId = ?");
            preparedStatement.setString(1, value);
            result = preparedStatement.executeQuery();

            if (result.next()){
                type = new Type(result.getInt("pKey_typeId"), result.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return type;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public void create(String tableName, Type object) {

    }

    @Override
    public void update(String tableName, Type object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
