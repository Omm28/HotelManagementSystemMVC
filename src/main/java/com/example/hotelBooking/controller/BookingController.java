package com.example.hotelBooking.controller;

import com.example.hotelBooking.model.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BookingController {

    @PersistenceContext
    private EntityManager entityManager;

    // Displays list of hotels and supports optional search
    @GetMapping("/booking")
    public String showHotels(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Hotel> hotels;

        if (query != null && !query.isEmpty()) {
            String queryStr = "SELECT h FROM Hotel h WHERE " +
                    "LOWER(h.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
                    "LOWER(h.location) LIKE LOWER(CONCAT('%', :query, '%'))";
            TypedQuery<Hotel> typedQuery = entityManager.createQuery(queryStr, Hotel.class);
            typedQuery.setParameter("query", query);
            hotels = typedQuery.getResultList();
        } else {
            hotels = entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class).getResultList();
        }

        model.addAttribute("hotels", hotels);
        model.addAttribute("query", query); // so the search bar retains input

        return "booking"; // corresponds to booking.html
    }
}
