package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingRepository implements IObjectRepository<Booking> {

    private static Connection conn = DBConnection.getConnection();
    private PreparedStatement preparedStatement;
    private ResultSet result;

    public BookingRepository(){
    }

    @Override
    public ArrayList<Booking> readAll(String tableName) {
        ArrayList<Booking> bookings = new ArrayList<>();

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms_bookings");
            result = preparedStatement.executeQuery();

            while (result.next()){
                bookings.add(new Booking(result.getInt("pKey_bookingId"), result.getInt("fKey_mtrhmsId"), result.getInt("fKey_usersId"), result.getDate("startDate"), result.getInt("isCanceled"), result.getDate("cancellation_Date"), result.getInt("ppd"), result.getInt("extras_price"), result.getInt("pickUp_distance"), result.getInt("dropOff_distance"), result.getInt("dropOff_nrOfKm"), result.getDate("endDate"), result.getString("reservation_rental")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public Booking read(String tableName, String columnName, String value) {
        return null;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public Booking readId(int id) {
        return null;
    }

    @Override
    public void create(String tableName, Booking object) {

    }

    @Override
    public void update(String tableName, Booking object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
