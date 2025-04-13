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

    public List<Room> getAvailableRooms(Long hotelId, String type) {
        if (type != null && !type.isEmpty()) {
            return roomRepository.findByHotelIdAndTypeAndAvailableTrue(hotelId, type);
        } else {
            return roomRepository.findByHotelIdAndAvailableTrue(hotelId);
        }
    }
}
