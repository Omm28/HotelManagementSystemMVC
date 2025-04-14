package com.example.hotelBooking.payment;

public class CreditCardPaymentProcessor implements PaymentProcessor {

    @Override
    public boolean processPayment(double amount) {
        // Simulating payment success
        System.out.println("Processing credit card payment of $" + amount);
        return true; // Assume the payment is successful
    }
}
