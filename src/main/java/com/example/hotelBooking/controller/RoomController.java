package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms/{hotelId}")
    public String showAllRooms(@PathVariable Long hotelId, Model model) {
        List<Room> allRooms = roomService.getAllRoomsByHotelId(hotelId);
        long availableRoomsCount = allRooms.stream().filter(Room::isAvailable).count();

        model.addAttribute("rooms", allRooms);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("availableRoomsCount", availableRoomsCount);
        return "rooms";
    }
}