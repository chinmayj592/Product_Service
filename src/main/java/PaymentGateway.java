package com.example.paymentservice.paymentgateways;

import com.stripe.exception.StripeException;

public interface PaymentGateway {

    String initiatePayment(Long orderId) throws StripeException;
}
