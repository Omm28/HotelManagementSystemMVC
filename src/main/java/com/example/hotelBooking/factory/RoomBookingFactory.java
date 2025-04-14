package com.example.hotelBooking.factory;

import com.example.hotelBooking.model.RoomBooking;

public class RoomBookingFactory {

    public static RoomBooking createBooking(String reservationId, Long hotelId, Long roomId,
            String username, String checkInDate, String checkOutDate) {

        RoomBooking booking = new RoomBooking();
        booking.setReservationId(reservationId);
        booking.setHotelId(hotelId);
        booking.setRoomId(roomId);
        booking.setUsername(username);
        booking.setCheckInDate(checkInDate);
        booking.setCheckOutDate(checkOutDate);

        return booking;
    }
}
