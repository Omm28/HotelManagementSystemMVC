package com.example.hotelBooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotelBooking.model.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByHotelIdAndAvailableTrue(Long hotelId);

    List<Room> findByHotelIdAndTypeAndAvailableTrue(Long hotelId, String type);
}
