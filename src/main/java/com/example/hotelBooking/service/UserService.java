package com.example.hotelBooking.service;

import com.example.hotelBooking.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
}
