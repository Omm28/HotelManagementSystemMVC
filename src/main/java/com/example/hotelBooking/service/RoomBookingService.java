package com.example.hotelBooking.service;

import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.repository.RoomBookingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Transactional
    public String saveBooking(Long hotelId, Long roomId, String username, LocalDate startDate, LocalDate endDate) {
        String bookingId = UUID.randomUUID().toString();
        RoomBooking booking = new RoomBooking();
        booking.setReservationId(bookingId);
        booking.setHotelId(hotelId);
        booking.setRoomId(roomId);
        booking.setUsername(username);
        booking.setCheckInDate(startDate);
        booking.setCheckOutDate(endDate);
        booking.setStatus("CONFIRMED");
        roomBookingRepository.save(booking);
        return bookingId;
    }

    @Transactional
    public void cancelBooking(String reservationId) {
        RoomBooking booking = roomBookingRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        booking.setStatus("CANCELLED");
        roomBookingRepository.save(booking);
    }

    public boolean isRoomAvailable(Long roomId, LocalDate startDate, LocalDate endDate) {
        return !roomBookingRepository.isRoomBooked(roomId, startDate, endDate);
    }

    public List<RoomBooking> getUserBookings(String username) {
        return roomBookingRepository.findByUsername(username);
    }

    public RoomBooking getBookingByReservationId(String reservationId) {
        return roomBookingRepository.findById(reservationId).orElse(null);
    }

    @Transactional
    public void updateBooking(RoomBooking booking) {
        roomBookingRepository.save(booking);
    }
}