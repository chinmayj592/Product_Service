package com.example.paymentservice.controller;


import com.example.paymentservice.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }

    @PostMapping("/initiate/{orderId}")
    public String initiatePayment(@PathVariable("orderId") Long orderId) throws StripeException {
        // Logic to initiate a payment
        return paymentService.initiatePayment(orderId);
    }
}
