package com.example.hotelBooking.payment;

import com.example.hotelBooking.model.RoomBooking;

public interface PaymentStrategy {
    boolean processPayment(RoomBooking booking, double amount);
}