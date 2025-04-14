package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Hotel;
import com.example.hotelBooking.service.BookingService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BookingService bookingService; // Autowire the BookingService to access the search functionality

    // Unified method for displaying hotels and handling search functionality
    @GetMapping("/booking")
    public String showHotels(@RequestParam(value = "query", required = false) String query, Model model) {

        List<Hotel> hotels;

        // If search query is provided, use a custom query to search hotels by name or
        // location
        if (query != null && !query.isEmpty()) {
            String queryStr = "SELECT h FROM Hotel h WHERE 1=1 AND (LOWER(h.name) LIKE LOWER(CONCAT('%', :query, '%'))"
                    + " OR LOWER(h.location) LIKE LOWER(CONCAT('%', :query, '%')))";
            TypedQuery<Hotel> typedQuery = entityManager.createQuery(queryStr, Hotel.class);
            typedQuery.setParameter("query", query);
            hotels = typedQuery.getResultList();
        } else {
            // If no search term, get all hotels from the database
            hotels = entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
        }

        // Add the hotels and query to the model
        model.addAttribute("hotels", hotels);
        model.addAttribute("query", query);

        return "booking"; // Return the booking.html page with the filtered hotels
    }
}
