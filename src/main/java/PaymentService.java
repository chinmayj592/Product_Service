package com.example.paymentservice.services;


import com.example.paymentservice.paymentgateways.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway=paymentGateway;
    }

    public String initiatePayment(Long orderId) throws StripeException {
        return paymentGateway.initiatePayment(orderId);
    }

}
