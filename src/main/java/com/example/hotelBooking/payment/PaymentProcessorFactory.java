package com.example.hotelBooking.payment;

public class PaymentProcessorFactory {

    public static PaymentProcessor getPaymentProcessor(String type) {
        if (type.equalsIgnoreCase("CREDIT_CARD")) {
            return new CreditCardPaymentProcessor();
        } else if (type.equalsIgnoreCase("PAYPAL")) {
            return new PaypalPaymentProcessor();
        }
        return null;
    }
}
