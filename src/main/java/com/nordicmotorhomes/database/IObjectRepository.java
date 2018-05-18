package com.nordicmotorhomes.database;

import java.util.ArrayList;

public interface IObjectRepository<T> {

    ArrayList<T> readAll(String tableName);
    T read(String tableName, String columnName, String value);
    boolean readOne(String a, String b);
    void create(String tableName, T object);
    void update(String tableName, T object);
    void delete(String tableName, String columnName, String value);
}
