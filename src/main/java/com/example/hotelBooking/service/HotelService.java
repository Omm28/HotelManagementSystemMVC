package com.example.hotelBooking.service;

import com.example.hotelBooking.model.Hotel;
import com.example.hotelBooking.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

}
