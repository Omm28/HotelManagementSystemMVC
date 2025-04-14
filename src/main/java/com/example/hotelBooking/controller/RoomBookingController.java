package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.service.RoomBookingService;
import com.example.hotelBooking.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bookRoom")
public class RoomBookingController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomBookingService roomBookingService;

    // Display available rooms for a specific hotel
    @GetMapping("/{hotelId}")
    public String showRoomsInHotel(@PathVariable Long hotelId, Model model) {
        List<Room> rooms = roomService.getRoomsByHotelId(hotelId);
        model.addAttribute("rooms", rooms);
        model.addAttribute("hotelId", hotelId);
        return "rooms"; // changed from "Rooms" to "rooms"
    }

    // Display booking form for a selected room
    @GetMapping("/{hotelId}/{roomId}")
    public String showBookingForm(@PathVariable Long hotelId,
            @PathVariable Long roomId,
            Model model) {
        Room room = roomService.getRoomById(roomId);
        model.addAttribute("room", room);
        model.addAttribute("hotelId", hotelId); // added so template gets hotelId
        return "roomBooking";
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
        return "bookingSuccess";
    }
}
