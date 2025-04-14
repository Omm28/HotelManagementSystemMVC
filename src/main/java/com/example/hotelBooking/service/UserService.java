package com.example.hotelBooking.service;

import com.example.hotelBooking.model.User;
import com.example.hotelBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Removed PasswordEncoder to store passwords as plain text (only for
    // prototyping/testing)
    public User save(User user) {
        // Simply store the raw password - not recommended for production!
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    // You can add other user-related methods here as needed.
}
