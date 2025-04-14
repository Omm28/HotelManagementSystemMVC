package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Hotel;
import com.example.hotelBooking.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public String showBookingPage(@RequestParam(required = false) String keyword, Model model) {
        List<Hotel> hotels;
        if (keyword != null && !keyword.isEmpty()) {
            hotels = hotelService.searchHotelsByName(keyword);
        } else {
            hotels = hotelService.getAllHotels();
        }
        model.addAttribute("hotels", hotels);
        model.addAttribute("keyword", keyword);
        return "booking"; // booking.html in templates
    }
}
