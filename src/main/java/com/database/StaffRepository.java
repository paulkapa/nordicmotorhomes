package com.database;

import com.models.Staff;

import java.util.ArrayList;

public class StaffRepository implements IObjectRepository<Staff> {


    @Override
    public ArrayList<Staff> readAll(String tableName) {
        return null;
    }

    @Override
    public Staff read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public void create(String tableName, Staff object) {

    }

    @Override
    public void update(String tableName, Staff object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
