package com.example.hotelBooking.payment;

public interface PaymentProcessor {
    boolean processPayment(double amount);
}
