package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Repair;

import java.sql.*;
import java.util.ArrayList;

public class RepairRepository implements IObjectRepository<Repair> {

    private static Connection conn = DBConnection.getConnection();
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
                        result.getDate("startDate").toLocalDate(), result.getString("problem"),
                        result.getString("solution"), result.getDate("endDate").toLocalDate()));
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
        Repair repair = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms_repairs WHERE pKey_repairId = '" + id + "'");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                repair = new Repair(result.getInt("pKey_repairId"), result.getInt("fKey_mtrhmId"), result.getDate("startDate").toLocalDate(), result.getString("problem"), result.getString("solution"), result.getDate("endDate").toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repair;
    }

    @Override
    public void create(String tableName, Repair object) {

        try {
            preparedStatement = conn.prepareStatement("INSERT INTO mtrhms_repairs(fKey_mtrhmId, startDate, problem, solution, endDate) VALUES(?, ?, ? , ?, ?)");

            preparedStatement.setInt(1, object.getMotorhomeId());
            preparedStatement.setDate(2, Date.valueOf(object.getStartDate()));
            preparedStatement.setString(3, object.getProblem());
            preparedStatement.setString(4, object.getSolution());
            preparedStatement.setDate(5, Date.valueOf(object.getEndDate()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(String tableName, Repair object) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE mtrhms_repairs SET fKey_mtrhmId = ?, startDate = ?,  problem = ?, solution = ?, endDate = ? WHERE pKey_repairId = ?");

            preparedStatement.setInt(1, object.getMotorhomeId());
            preparedStatement.setDate(2, Date.valueOf(object.getStartDate()));
            preparedStatement.setString(3, object.getProblem());
            preparedStatement.setString(4, object.getSolution());
            preparedStatement.setDate(5, Date.valueOf(object.getEndDate()));
            preparedStatement.setInt(6, object.getRepairId());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
