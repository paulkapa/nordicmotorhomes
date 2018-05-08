package com.database;

import java.util.ArrayList;

public interface IObjectRepository {

    ArrayList<Object> readAll();
    Object read(int id);
    void create(Object object);
    void update(Object object);
    void delete(int id);
}
