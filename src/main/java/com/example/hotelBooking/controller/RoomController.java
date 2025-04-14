package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RoomController {

    @GetMapping("/rooms/{hotelId}")
    public String showAvailableRooms(@PathVariable Long hotelId, Model model) {
        // logic to get rooms for hotel
        model.addAttribute("hotelId", hotelId); // or actual rooms
        return "rooms"; // rooms.html
    }
}
