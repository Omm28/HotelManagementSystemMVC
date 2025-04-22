package com.example.hotelBooking.factory;

import com.example.hotelBooking.model.RoomBooking;
import java.time.LocalDate;

public class RoomBookingFactory {

    public static RoomBooking createRoomBooking(Long hotelId, Long roomId, String username,
            String checkInDate, String checkOutDate) {
        RoomBooking booking = new RoomBooking();
        booking.setHotelId(hotelId);
        booking.setRoomId(roomId);
        booking.setUsername(username);
        booking.setCheckInDate(LocalDate.parse(checkInDate));
        booking.setCheckOutDate(LocalDate.parse(checkOutDate));
        booking.setStatus("CONFIRMED");
        return booking;
    }
}
