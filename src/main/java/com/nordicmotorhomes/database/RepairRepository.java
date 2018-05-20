package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Repair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairRepository implements IObjectRepository {

    private static Connection conn = DBConnection.getConnection();;
    private PreparedStatement preparedStatement;
    private ResultSet result;


    @Override
    public ArrayList readAll(String tableName) {
        ArrayList<Repair> repairList = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms_repairs");
            result = preparedStatement.executeQuery();

            while (result.next()){
                repairList.add(new Repair(result.getInt("pKey_repairId"), result.getInt("fKey_mtrhmId"),
                        result.getDate("startDate").toLocalDate(), result.getString("problem"),
                        result.getString("solution"), result.getDate("endDate").toLocalDate()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repairList;
    }


    @Override
    public Object read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public void create(String tableName, Object object) {

    }

    @Override
    public void update(String tableName, Object object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
