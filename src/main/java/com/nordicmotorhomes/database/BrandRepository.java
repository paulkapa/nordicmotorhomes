package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandRepository implements IObjectRepository<Brand> {

    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public BrandRepository(){
        this.conn = DBConnection.getConnection();
    }

    @Override
    public ArrayList<Brand> readAll(String tableName) {
        return null;
    }

    @Override
    public Brand read(String tableName, String columnName, String value) {
        Brand brand = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM brands WHERE pKey_brandId = ?");
            preparedStatement.setString(1, value);
            result = preparedStatement.executeQuery();

            if (result.next()){
                brand = new Brand(result.getInt("pKey_brandId"), result.getString("brand"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brand;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public void create(String tableName, Brand object) {

    }

    @Override
    public void update(String tableName, Brand object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
