package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.Booking;
import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {
    List<RoomBooking> findByUser(User user);
}
