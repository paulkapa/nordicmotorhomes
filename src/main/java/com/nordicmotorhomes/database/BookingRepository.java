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
                bookings.add(new Booking(result.getInt("pKey_bookingId"), result.getInt("fKey_mtrhmsId"), result.getInt("fKey_usersId"), result.getDate("startDate"), result.getInt("isCanceled"), result.getDate("cancellation_Date"), result.getInt("ppd"), result.getInt("extras_price"), result.getInt("pickUp_distance"),
                        result.getInt("dropOff_distance"), result.getInt("dropOff_nrOfKm"), result.getDate("endDate"), result.getString("reservation_rental"), result.getInt("total_price"), result.getString("isPaid")));
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

        try {

            preparedStatement = conn.prepareStatement("INSERT INTO mtrhms_bookings(fKey_mtrhmsId, fKey_usersId, startDate, isCanceled, cancellation_date, ppd, extras_price, pickUp_distance, dropOff_distance, dropOff_nrOfKm, endDate, reservation_rental, total_price, isPaid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, object.getMtrhmId());
            preparedStatement.setInt(2, object.getUserId());
            preparedStatement.setDate(3, object.getStartDate());
            preparedStatement.setInt(4, object.getIsCancelled());
            preparedStatement.setDate(5, object.getCancellationDate());
            preparedStatement.setInt(6, object.getPpd());
            preparedStatement.setInt(7, object.getExtrasPrice());
            preparedStatement.setInt(8, object.getPickUpDistance());
            preparedStatement.setInt(9, object.getDropOffDistance());
            preparedStatement.setInt(10, object.getDropOffKmNr());
            preparedStatement.setDate(11, object.getEndDate());
            preparedStatement.setString(12, object.getReservationRental());
            preparedStatement.setInt(13, object.getTotalPrice());
            preparedStatement.setString(14, object.getIsPaid());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String tableName, Booking object) {

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
