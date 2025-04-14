package com.example.hotelBooking.payment;

public class PaypalPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount) {
        // Simulating payment success
        System.out.println("Processing PayPal payment of $" + amount);
        return true; // Assume the payment is successful
    }
}
