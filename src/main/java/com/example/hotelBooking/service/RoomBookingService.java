package com.example.hotelBooking.service;

import com.example.hotelBooking.factory.RoomBookingFactory;
import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.repository.RoomBookingRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Import this

@Service
public class RoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Transactional // Add this annotation
    public String saveBooking(Long hotelId, Long roomId, String username, String startDate, String endDate) {
        String reservationId = UUID.randomUUID().toString();
        RoomBooking booking = RoomBookingFactory.createBooking(reservationId, hotelId, roomId, username, startDate,
                endDate);
        roomBookingRepository.save(booking);
        return reservationId; // Return the ID
    }

    // Add a method to check for booking conflicts
    public boolean isRoomAvailable(Long hotelId, Long roomId, String startDate, String endDate) {
        return !roomBookingRepository.isRoomBooked(hotelId, roomId, startDate, endDate);
    }
}