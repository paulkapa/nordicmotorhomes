package com.database;

import java.util.ArrayList;

public interface IObjectRepository<T> {

    ArrayList<T> readAll(String tableName);
    T read(String tableName, String columnName, String value);
    void create(String tableName, T object);
    void update(String tableName, T object);
    void delete(String tableName, String columnName, String value);
}
