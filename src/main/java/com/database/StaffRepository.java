package com.database;

import java.util.ArrayList;

public class StaffRepository implements IObjectRepository {

    @Override
    public ArrayList<Object> readAll(String tableName) {
        return null;
    }

    @Override
    public Object read(String tableName, String columnName, String value) {

        return null;
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
