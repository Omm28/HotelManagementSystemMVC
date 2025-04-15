package com.example.hotelBooking.controller;

import java.time.LocalDate;

import com.example.hotelBooking.model.*;
import com.example.hotelBooking.repository.HotelRepository;
import com.example.hotelBooking.repository.RoomRepository;
import com.example.hotelBooking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class RoomBookingController {

        @Autowired
        private HotelService hotelService;

        @Autowired
        private UserService userService;

        @Autowired
        private RoomRepository roomRepository;

        @Autowired
        private RoomBookingService roomBookingService;

        @Autowired
        private HotelRepository hotelRepository;

        // Show room booking page
        @GetMapping("/booking/roomBooking/{hotelId}/{roomId}")
        public String showRoomBookingPage(@PathVariable Long hotelId,
                        @PathVariable Long roomId,
                        Model model) {
                Room room = roomRepository.findById(roomId)
                                .orElseThrow(() -> new RuntimeException("Room not found"));

                Hotel hotel = hotelRepository.findById(hotelId)
                                .orElseThrow(() -> new IllegalArgumentException("Invalid hotel ID"));
                model.addAttribute("hotel", hotel);
                model.addAttribute("room", room);
                return "roomBooking";
        }

        // Handle room booking submission
        @PostMapping("/roomBooking/submit")
        public String submitBooking(@RequestParam("hotelId") Long hotelId,
                        @RequestParam("roomId") Long roomId,
                        @RequestParam("checkInDate") String checkInDate,
                        @RequestParam("checkOutDate") String checkOutDate,
                        Authentication authentication,
                        Model model) {

                Hotel hotel = hotelService.getHotelById(hotelId)
                                .orElseThrow(() -> new RuntimeException("Hotel not found"));
                Room room = roomRepository.findById(roomId)
                                .orElseThrow(() -> new RuntimeException("Room not found"));
                User user = userService.findByUsername(authentication.getName())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                RoomBooking booking = new RoomBooking();
                booking.setHotel(hotel);
                booking.setRoom(room);
                booking.setUser(user);
                booking.setCheckInDate(LocalDate.parse(checkInDate));
                booking.setCheckOutDate(LocalDate.parse(checkOutDate));

                roomBookingService.saveBooking(booking);
                model.addAttribute("booking", booking);

                return "bookingConfirmation";
        }

        // Optional confirm method (can be removed if not used)
        @GetMapping("/confirm")
        public String confirmBooking(@RequestParam("hotelId") Long hotelId,
                        @RequestParam("roomId") Long roomId,
                        @RequestParam("numRooms") int numRooms,
                        Authentication authentication,
                        Model model) {
                Hotel hotel = hotelService.getHotelById(hotelId)
                                .orElseThrow(() -> new RuntimeException("Hotel not found"));
                Room room = roomRepository.findById(roomId)
                                .orElseThrow(() -> new RuntimeException("Room not found"));

                User user = userService.findByUsername(authentication.getName())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                RoomBooking booking = new RoomBooking();
                booking.setHotel(hotel);
                booking.setRoom(room);
                booking.setUser(user);

                roomBookingService.saveBooking(booking);
                model.addAttribute("booking", booking);

                return "bookingConfirmation";
        }
}
