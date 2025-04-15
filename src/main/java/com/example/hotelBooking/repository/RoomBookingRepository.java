package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {

    @Query("SELECT COUNT(rb) > 0 FROM RoomBooking rb WHERE rb.hotelId = :hotelId AND rb.roomId = :roomId AND " +
            "((rb.checkInDate <= :checkOutDate AND rb.checkOutDate >= :checkInDate))")
    boolean isRoomBooked(@Param("hotelId") Long hotelId,
            @Param("roomId") Long roomId,
            @Param("checkInDate") String checkInDate,
            @Param("checkOutDate") String checkOutDate);
}