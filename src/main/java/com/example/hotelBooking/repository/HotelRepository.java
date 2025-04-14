package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Custom search method to find hotels by name (case-insensitive)
    List<Hotel> findByNameContainingIgnoreCase(String keyword);
}
