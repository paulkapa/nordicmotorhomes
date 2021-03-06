package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Modela;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelRepository implements IObjectRepository<Modela> {

    private static Connection conn = DBConnection.getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public ModelRepository(){
    }

    @Override
    public ArrayList<Modela> readAll(String tableName) {
        ArrayList<Modela> modelList = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM models");
            result = preparedStatement.executeQuery();

            while (result.next()){
                modelList.add(new Modela(result.getInt("pKey_modelId"), result.getString("model"),
                        result.getInt("max_capacity"), result.getInt("fuelTank_volume"),
                        result.getInt("ppd")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    @Override
    public Modela read(String tableName, String columnName, String value) {
        Modela model = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM models WHERE pKey_modelId = ?");
            preparedStatement.setString(1, value);
            result = preparedStatement.executeQuery();

            if (result.next()){
                model = new Modela(result.getInt("pKey_modelId"), result.getString("model"), result.getInt("max_capacity"), result.getInt("fuelTank_volume"), result.getInt("ppd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public Modela readId(int id) {
        return null;
    }

    @Override
    public void create(String tableName, Modela object) {
        try {

            preparedStatement = conn.prepareStatement("INSERT INTO  models(model, max_capacity, fuelTank_volume, ppd) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, object.getModel());
            preparedStatement.setInt(2, object.getMaxCapacity());
            preparedStatement.setInt(3, object.getFuelTankVolume());
            preparedStatement.setInt(4, object.getPpd());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String tableName, Modela object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
