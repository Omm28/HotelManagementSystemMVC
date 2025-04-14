package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByAvailableIsTrueAndNumberOfBedsAndHotel_LocationContaining(int numberOfBeds, String location);

    List<Room> findByHotelIdAndAvailableTrue(Long hotelId);
}
