package com.example.hotelBooking.payment;

import com.example.hotelBooking.model.RoomBooking;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class UPIPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(RoomBooking booking, double amount) {
        // Simulate payment processing with 85% success rate
        Random random = new Random();
        boolean isSuccess = random.nextDouble() < 0.85;

        if (isSuccess) {
            System.out.println("UPI Payment processed successfully for booking: " + booking.getReservationId());
        } else {
            System.out.println("UPI Payment failed for booking: " + booking.getReservationId());
        }

        return isSuccess;
    }
}