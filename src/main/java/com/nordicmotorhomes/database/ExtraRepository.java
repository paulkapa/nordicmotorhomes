package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Extra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExtraRepository implements IObjectRepository<Extra> {

    private static Connection conn = DBConnection.getConnection();;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    @Override
    public ArrayList<Extra> readAll(String tableName) {
        ArrayList<Extra> repairList = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM extras");
            result = preparedStatement.executeQuery();

            while (result.next()){
                repairList.add(new Extra(result.getInt("pKey_extraId"), result.getString("extra"), result.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repairList;
    }

    @Override
    public Extra read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public Extra readId(int id) {
        Extra extra = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM extras WHERE pKey_extraId = '"+ id +"'");
            result = preparedStatement.executeQuery();

            if (result.next()){
                extra = new Extra(result.getInt("pKey_extraId"), result.getString("extra"), result.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extra;
    }

    @Override
    public void create(String tableName, Extra object) {
        try {

            preparedStatement = conn.prepareStatement("INSERT INTO extras(extra, price) VALUE (?, ?)");

            preparedStatement.setString(1, object.getExtra());
            preparedStatement.setInt(2, object.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String tableName, Extra object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
