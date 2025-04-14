package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Hotel;
import com.example.hotelBooking.service.HotelService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public String viewHotels(@RequestParam(required = false) String keyword, Model model) {
        List<Hotel> hotels;

        if (keyword != null && !keyword.isEmpty()) {
            hotels = hotelService.searchHotelsByName(keyword);
        } else {
            hotels = hotelService.getAllHotels();
        }

        model.addAttribute("hotels", hotels);
        model.addAttribute("keyword", keyword);

        return "hotels"; // Use the template you actually want to render
    }

}
