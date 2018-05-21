package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Repair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairRepository implements IObjectRepository<Repair> {

    private static Connection conn = DBConnection.getConnection();;
    private PreparedStatement preparedStatement;
    private ResultSet result;


    @Override
    public ArrayList<Repair> readAll(String tableName) {
        ArrayList<Repair> repairList = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms_repairs");
            result = preparedStatement.executeQuery();

            while (result.next()){
                repairList.add(new Repair(result.getInt("pKey_repairId"), result.getInt("fKey_mtrhmId"),
                        result.getDate("startDate"), result.getString("problem"),
                        result.getString("solution"), result.getDate("endDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repairList;
    }


    @Override
    public Repair read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public Repair readId(int id) {
        return null;
    }

    @Override
    public void update(String tableName, Repair object) {

    }

    @Override
    public void create(String tableName, Repair object) {
        try {

            preparedStatement = conn.prepareStatement("INSERT INTO mtrhms_repairs(startDate, problem, solution, endDate)  VALUE (?, ?, ?, ?)");

            preparedStatement.setDate(1, object.getStartDate());
            preparedStatement.setString(2, object.getProblem());
            preparedStatement.setString(3, object.getSolution());
            preparedStatement.setDate(4, object.getEndDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
