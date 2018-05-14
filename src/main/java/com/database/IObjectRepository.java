package com.database;

import java.util.ArrayList;

public interface IObjectRepository {

    ArrayList<Object> readAll(String tableName);
    Object read(String tableName, String columnName, String value);
    void create(String tableName, Object object);
    void update(String tableName, Object object);
    void delete(String tableName, String columnName, String value);
}
