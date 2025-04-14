package com.example.hotelBooking.service;

import com.example.hotelBooking.factory.RoomBookingFactory;
import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    public void saveBooking(Long hotelId, Long roomId, String username, String startDate, String endDate) {
        String reservationId = UUID.randomUUID().toString();
        RoomBooking booking = RoomBookingFactory.createBooking(reservationId, hotelId, roomId, username, startDate,
                endDate);
        roomBookingRepository.save(booking);
    }
}
