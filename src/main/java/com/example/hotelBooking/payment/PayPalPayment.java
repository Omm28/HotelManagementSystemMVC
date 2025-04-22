package com.example.hotelBooking.payment;

import com.example.hotelBooking.model.RoomBooking;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class PayPalPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(RoomBooking booking, double amount) {
        // Simulate payment processing with 90% success rate
        Random random = new Random();
        boolean isSuccess = random.nextDouble() < 0.9;

        if (isSuccess) {
            System.out.println("PayPal Payment processed successfully for booking: " + booking.getReservationId());
        } else {
            System.out.println("PayPal Payment failed for booking: " + booking.getReservationId());
        }

        return isSuccess;
    }
}