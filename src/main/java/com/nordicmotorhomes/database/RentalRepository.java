package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Booking;

import java.sql.*;
import java.util.ArrayList;


public class RentalRepository implements IObjectRepository<Booking> {

    private static Connection conn = DBConnection.getConnection();
    private PreparedStatement preparedStatement;
    //private ResultSet result;

    @Override
    public ArrayList<Booking> readAll(String tableName) {
        return null;
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
        try {

            preparedStatement = conn.prepareStatement("INSERT INTO mtrhms_bookings(fKey_mtrhmsId, fKey_usersId, startDate, isCanceled, cancellation_date, " +
                    "ppd, extras_price, pickUp_distance, dropOff_distance, dropOff_nrOfKm, endDate, reservation_rental, total_price, isPaid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, object.getMtrhmId());
            preparedStatement.setInt(2, object.getUserId());
            preparedStatement.setDate(3, Date.valueOf(object.getStartDate()));
            preparedStatement.setInt(4, 0);
            preparedStatement.setDate(5, Date.valueOf("9999-12-12"));
            preparedStatement.setInt(6, getPricePerDay(object.getMtrhmId()));
            preparedStatement.setInt(7, object.getExtrasPrice());
            preparedStatement.setInt(8, object.getPickUpDistance());
            preparedStatement.setInt(9, object.getDropOffDistance());
            preparedStatement.setInt(10, object.getDropOffKmNr());
            preparedStatement.setDate(11, Date.valueOf(object.getEndDate()));
            preparedStatement.setString(12, "rental");
            preparedStatement.setInt(13, 0);
            preparedStatement.setString(14, "no");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getPricePerDay(int value) {
        PreparedStatement preparedStatement;
        ResultSet result;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM models WHERE pKey_modelId = '" + value + "'");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                return result.getInt("ppd");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void update(String tableName, Booking object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
