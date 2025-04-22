package com.example.hotelBooking.repository;

import com.example.hotelBooking.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, String> {

    @Query("SELECT COUNT(r) > 0 FROM RoomBooking r WHERE r.roomId = :roomId " +
            "AND ((r.checkInDate <= :endDate AND r.checkOutDate >= :startDate) " +
            "OR (r.checkInDate <= :endDate AND r.checkOutDate >= :startDate))")
    boolean isRoomBooked(@Param("roomId") Long roomId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    List<RoomBooking> findByUsername(String username);
}