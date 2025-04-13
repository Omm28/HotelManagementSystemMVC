package com.example.hotelBooking.service;

import com.example.hotelBooking.model.Hotel;
import com.example.hotelBooking.repository.HotelRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
