package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Show all rooms for a hotel
    @GetMapping("/{hotelId}")
    public String showAllRooms(@PathVariable Long hotelId, Model model) {

        List<Room> allRooms = roomService.getAllRoomsByHotelId(hotelId);
        long availableRoomsCount = allRooms.stream().filter(Room::isAvailable).count();

        model.addAttribute("rooms", allRooms);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("availableRoomsCount", availableRoomsCount);
        return "rooms"; // rooms.html
    }

    // Optional: Endpoint to view details of a selected room for booking
    @GetMapping("/{hotelId}/book/{roomId}")
    public String bookRoom(@PathVariable Long hotelId,
            @PathVariable Long roomId,
            Model model) {
        Room room = roomService.getRoomById(roomId);
        if (room == null || !room.isAvailable()) {
            model.addAttribute("error", "Selected room is not available.");
            return "error"; // Optional error.html
        }

        model.addAttribute("room", room);
        model.addAttribute("hotelId", hotelId);
        return "booking"; // booking.html
    }
}
