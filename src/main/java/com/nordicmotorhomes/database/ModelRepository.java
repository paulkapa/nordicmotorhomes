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
        return null;
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
    public void create(String tableName, Modela object) {

    }

    @Override
    public void update(String tableName, Modela object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
