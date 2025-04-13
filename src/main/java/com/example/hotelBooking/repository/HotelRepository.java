package com.example.hotelBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotelBooking.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
