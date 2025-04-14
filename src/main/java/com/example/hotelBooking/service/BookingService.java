package com.example.hotelBooking.service;

import com.example.hotelBooking.model.Booking;
import com.example.hotelBooking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Method to return all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll(); // Fetches all bookings
    }

    // Method to search bookings based on hotel name or location
    public List<Booking> searchBookings(String query) {
        if (query != null && !query.isEmpty()) {
            return bookingRepository.findByHotelNameOrLocation(query); // Search by hotel name or location
        }

        // Return all bookings if no query is provided
        return bookingRepository.findAll();
    }
}