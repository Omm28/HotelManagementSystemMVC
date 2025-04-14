package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Query to search hotels by hotel name or location
    @Query("SELECT b FROM Booking b WHERE LOWER(b.hotel.name) LIKE %:query% OR LOWER(b.hotel.location) LIKE %:query%")
    List<Booking> findByHotelNameOrLocation(@Param("query") String query);
}