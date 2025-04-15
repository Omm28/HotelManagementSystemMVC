package com.example.hotelBooking.service;

import com.example.hotelBooking.model.Booking;
import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.repository.BookingRepository;
import com.example.hotelBooking.repository.RoomBookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    public void saveBooking(RoomBooking booking) {
        roomBookingRepository.save(booking);
    }
}
