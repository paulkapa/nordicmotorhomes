package com.nordicmotorhomes.database;

import java.util.ArrayList;

public interface IObjectRepository<T> {

    ArrayList<T> readAll(String tableName);
    T read(String tableName, String columnName, String value);
    boolean readOne(String tableName, String value1, String value2);
    void create(String tableName, T object);
    void update(String tableName, T object);
    void delete(String tableName, String columnName, String value);
}
