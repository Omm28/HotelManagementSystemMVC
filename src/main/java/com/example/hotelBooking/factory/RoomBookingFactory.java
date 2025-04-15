package com.example.hotelBooking.factory;

import com.example.hotelBooking.model.Hotel;
import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.model.User;

import java.time.LocalDate;

public class RoomBookingFactory {

    public static RoomBooking createBooking(Long Id, Hotel hotel, Room room,
            User user, String checkInDate, String checkOutDate) {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setId(Id);
        roomBooking.setHotel(hotel);
        roomBooking.setRoom(room);
        roomBooking.setUser(user);
        roomBooking.setCheckInDate(LocalDate.parse(checkInDate)); // Convert String -> LocalDate
        roomBooking.setCheckOutDate(LocalDate.parse(checkOutDate)); // Convert String -> LocalDate
        return roomBooking;
    }

}
