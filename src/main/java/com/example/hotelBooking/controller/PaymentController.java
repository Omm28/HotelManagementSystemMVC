package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Room;
import com.example.hotelBooking.model.RoomBooking;
import com.example.hotelBooking.payment.PaymentContext;
import com.example.hotelBooking.service.RoomBookingService;
import com.example.hotelBooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentContext paymentContext;

    @Autowired
    private RoomBookingService roomBookingService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/{reservationId}")
    public String showPaymentPage(@PathVariable String reservationId, Model model) {
        RoomBooking booking = roomBookingService.getBookingByReservationId(reservationId);
        if (booking == null) {
            return "redirect:/bookings";
        }

        // Fetch and set the room information
        Room room = roomService.getRoomById(booking.getRoomId());
        booking.setRoom(room);

        model.addAttribute("booking", booking);
        return "payment";
    }

    @PostMapping("/process")
    public String processPayment(@RequestParam String reservationId,
            @RequestParam String paymentMethod,
            @RequestParam double amount,
            Model model) {
        RoomBooking booking = roomBookingService.getBookingByReservationId(reservationId);
        if (booking == null) {
            return "redirect:/bookings";
        }

        // Fetch and set the room information
        Room room = roomService.getRoomById(booking.getRoomId());
        booking.setRoom(room);

        try {
            boolean isSuccess = paymentContext.processPayment(paymentMethod, booking, amount);
            if (isSuccess) {
                booking.setStatus("PAID");
                roomBookingService.updateBooking(booking);
                model.addAttribute("success", true);
                model.addAttribute("message", "Payment processed successfully!");
            } else {
                model.addAttribute("success", false);
                model.addAttribute("message", "Payment failed. Please try again.");
            }
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("message", "Error processing payment: " + e.getMessage());
        }

        model.addAttribute("booking", booking);
        return "paymentResult";
    }
}