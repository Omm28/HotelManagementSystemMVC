package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.service.RoomService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public String showAvailableRooms(@RequestParam Long hotelId, @RequestParam(required = false) String type,
            Model model) {
        List<Room> rooms = roomService.getAvailableRooms(hotelId, type);
        model.addAttribute("rooms", rooms);
        return "rooms";
    }

}
