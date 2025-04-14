package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Add custom queries later if needed
}
