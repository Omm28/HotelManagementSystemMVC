package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.service.RoomBookingService;
import com.example.hotelBooking.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookRoom")
public class RoomBookingController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomBookingService roomBookingService;

    // Display booking form for a selected room
    @GetMapping("/{hotelId}/{roomId}")
    public String showBookingForm(@PathVariable Long hotelId,
            @PathVariable Long roomId,
            Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("hotelId", hotelId);
        return "roomBooking"; // Ensure you have a roomBooking.html file
    }

    // Handle booking submission
    @PostMapping("/submit")
    public String submitBooking(@RequestParam Long hotelId,
            @RequestParam Long roomId,
            @RequestParam String username,
            @RequestParam String checkInDate,
            @RequestParam String checkOutDate,
            Model model) {
        roomBookingService.saveBooking(hotelId, roomId, username, checkInDate, checkOutDate);
        model.addAttribute("message", "Booking Successful!");
        return "bookingSuccess"; // Ensure you have a bookingSuccess.html file
    }
}