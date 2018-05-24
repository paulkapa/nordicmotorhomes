package com.nordicmotorhomes.database;

import com.nordicmotorhomes.model.Booking;

import java.sql.*;
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
                bookings.add(new Booking(result.getInt("pKey_bookingId"), result.getInt("fKey_mtrhmsId"), result.getInt("fKey_usersId"), result.getDate("startDate").toLocalDate(), result.getInt("isCanceled"), result.getDate("cancellation_Date").toLocalDate(), result.getInt("ppd"), result.getInt("extras_price"), result.getInt("pickUp_distance"),
                        result.getInt("dropOff_distance"), result.getInt("dropOff_nrOfKm"), result.getDate("endDate").toLocalDate(), result.getString("reservation_rental"), result.getInt("total_price"), result.getString("isPaid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public Booking read(String tableName, String columnName, String value) {

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms_bookings WHERE pKey_bookingId = '"+ value +"'");
            result = preparedStatement.executeQuery();

            if (result.next()){
                return new Booking(result.getInt("pKey_bookingId"), result.getInt("fKey_mtrhmsId"), result.getInt("fKey_usersId"), result.getDate("startDate").toLocalDate(), result.getInt("isCanceled"), result.getDate("cancellation_Date").toLocalDate(), result.getInt("ppd"), result.getInt("extras_price"), result.getInt("pickUp_distance"),
                        result.getInt("dropOff_distance"), result.getInt("dropOff_nrOfKm"), result.getDate("endDate").toLocalDate(), result.getString("reservation_rental"), result.getInt("total_price"), result.getString("isPaid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean readOne(String tableName, String value1, String value2) {
        return false;
    }

    @Override
    public Booking readId(int id) {
        Booking booking = null;

        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM mtrhms_bookings WHERE pKey_bookingId = '" + id + "'");
            result = preparedStatement.executeQuery();

            if (result.next()) {
                booking = new Booking(result.getInt("pKey_bookingId"), result.getInt("fKey_mtrhmsId"), result.getInt("fKey_usersId"), result.getDate("startDate").toLocalDate(), result.getInt("isCanceled"), result.getDate("cancellation_Date").toLocalDate(), result.getInt("ppd"), result.getInt("extras_price"), result.getInt("pickUp_distance"),
                        result.getInt("dropOff_distance"), result.getInt("dropOff_nrOfKm"), result.getDate("endDate").toLocalDate(), result.getString("reservation_rental"), result.getInt("total_price"), result.getString("isPaid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
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
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);
            preparedStatement.setInt(9, 0);
            preparedStatement.setInt(10, 0);
            preparedStatement.setDate(11, Date.valueOf(object.getEndDate()));
            preparedStatement.setString(12, "reservation");
            preparedStatement.setInt(13, 0);
            preparedStatement.setString(14, "no");

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void update(String tableName, Booking object) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE mtrhms_bookings SET fKey_mtrhmsId = ?, fKey_usersId = ?," +
                    "startDate = ?, isCanceled = ?, cancellation_date = ?, ppd = ?, extras_price = ?, pickUp_distance = ?," +
                    "dropOff_distance = ?, dropOff_nrOfKm = ?, endDate = ?, reservation_rental = ?, total_price = ?, isPaid = ?  WHERE pKey_bookingId = ?");

            preparedStatement.setInt(1, object.getMtrhmId());
            preparedStatement.setInt(2, object.getUserId());
            preparedStatement.setDate(3, Date.valueOf(object.getStartDate()));
            preparedStatement.setInt(4, object.getIsCancelled());
            preparedStatement.setDate(5, Date.valueOf(object.getCancellationDate()));
            preparedStatement.setInt(6, object.getPpd());
            preparedStatement.setInt(7, object.getExtrasPrice());
            preparedStatement.setInt(8, object.getPickUpDistance());
            preparedStatement.setInt(9, object.getDropOffDistance());
            preparedStatement.setInt(10, object.getDropOffKmNr());
            preparedStatement.setDate(11, Date.valueOf(object.getEndDate()));
            preparedStatement.setString(12, object.getReservationRental());
            preparedStatement.setInt(13, object.getTotalPrice());
            preparedStatement.setString(14, object.getIsPaid());
            preparedStatement.setInt(15, object.getId());


            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(String tableName, String columnName, String value) {

    }
}
