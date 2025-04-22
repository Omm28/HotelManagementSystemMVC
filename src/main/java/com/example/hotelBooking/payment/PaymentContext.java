package com.example.hotelBooking.payment;

import com.example.hotelBooking.model.RoomBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PaymentContext {
    @Autowired
    private Map<String, PaymentStrategy> paymentStrategies;

    public boolean processPayment(String paymentMethod, RoomBooking booking, double amount) {
        // Convert payment method to match bean naming convention (e.g., "creditCard" ->
        // "creditCardPayment")
        String strategyName = convertToStrategyName(paymentMethod);
        PaymentStrategy strategy = paymentStrategies.get(strategyName);

        if (strategy == null) {
            throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
        return strategy.processPayment(booking, amount);
    }

    private String convertToStrategyName(String paymentMethod) {
        // Handle null case
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }

        // Convert camelCase to proper bean name (e.g., "creditCard" ->
        // "creditCardPayment")
        switch (paymentMethod.toLowerCase()) {
            case "creditcard":
                return "creditCardPayment";
            case "upi":
                return "UPIPayment";
            case "paypal":
                return "payPalPayment";
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}