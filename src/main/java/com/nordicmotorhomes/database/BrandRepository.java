package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandRepository implements IObjectRepository<Brand> {

    private static Connection conn = DBConnection.getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public BrandRepository(){
    }

    @Override
    public ArrayList<Brand> readAll(String tableName) {
        ArrayList<Brand> brandList = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM brands");
            result = preparedStatement.executeQuery();

            while (result.next()){
                brandList.add(new Brand(result.getInt("pKey_brandId"), result.getString("brand")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return brandList;
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
    public Brand readId(int id) {
        return null;
    }

    @Override
    public void create(String tableName, Brand object) {
        try {

            preparedStatement = conn.prepareStatement("INSERT INTO  brands(brand) VALUES (?)");

            preparedStatement.setString(1, object.getBrand());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String tableName, Brand object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
