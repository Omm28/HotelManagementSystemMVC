package com.example.hotelBooking.service;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public List<Room> getAllRoomsByHotelId(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
}