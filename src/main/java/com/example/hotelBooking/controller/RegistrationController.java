package com.example.hotelBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.hotelBooking.repository.UserRepository;

import jakarta.validation.Valid;

import com.example.hotelBooking.dto.RegistrationDto;
import com.example.hotelBooking.model.User;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new RegistrationDto());
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute("user") RegistrationDto registrationDto,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "register";
        }

        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            model.addAttribute("usernameError", "Username is already taken!");
            return "register";
        }

        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        userRepository.save(user);

        return "redirect:/login?registered";
    }
}