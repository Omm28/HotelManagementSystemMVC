package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.service.RoomBookingService;
import com.example.hotelBooking.service.RoomService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class RoomBookingController {

        @Autowired
        private RoomService roomService;

        @Autowired
        private RoomBookingService roomBookingService;

        // Display booking form for a selected room
        @GetMapping("/bookRoom/{hotelId}/{roomId}")
        public String showBookingForm(@PathVariable Long hotelId,
                        @PathVariable Long roomId,
                        Model model) {
                Room room = roomService.getRoomById(roomId);
                model.addAttribute("room", room);
                model.addAttribute("hotelId", hotelId);
                return "roomBooking";
        }

        // Handle booking submission
        @PostMapping("/bookRoom/submit")
        public String submitBooking(@RequestParam Long hotelId,
                        @RequestParam Long roomId,
                        @RequestParam String checkInDate,
                        @RequestParam String checkOutDate,
                        Model model) {
                // Get the logged-in user's username
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();

                // Get the room object for the form
                Room room = roomService.getRoomById(roomId);
                model.addAttribute("room", room);
                model.addAttribute("hotelId", hotelId);

                // Convert string dates to LocalDate
                LocalDate startDate = LocalDate.parse(checkInDate);
                LocalDate endDate = LocalDate.parse(checkOutDate);

                // Check if room is available
                if (!roomBookingService.isRoomAvailable(roomId, startDate, endDate)) {
                        model.addAttribute("error", "Room is not available for the selected dates");
                        return "roomBooking";
                }

                String bookingId = roomBookingService.saveBooking(hotelId, roomId, username, startDate, endDate);
                model.addAttribute("reservationId", bookingId);
                model.addAttribute("checkInDate", checkInDate);
                model.addAttribute("checkOutDate", checkOutDate);
                model.addAttribute("username", username);
                return "bookingConfirmation";
        }

        // Display user's bookings
        @GetMapping("/bookings")
        public String showUserBookings(Model model) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String username = authentication.getName();

                List<RoomBooking> userBookings = roomBookingService.getUserBookings(username);
                model.addAttribute("bookings", userBookings);
                return "userBookings";
        }

        // Handle booking cancellation
        @PostMapping("/bookRoom/cancel/{reservationId}")
        public String cancelBooking(@PathVariable String reservationId) {
                roomBookingService.cancelBooking(reservationId);
                return "redirect:/bookings";
        }
}