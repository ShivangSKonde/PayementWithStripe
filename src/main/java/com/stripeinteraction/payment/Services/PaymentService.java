package com.stripeinteraction.payment.Services;


import com.stripe.exception.StripeException;

public interface PaymentService {
    public String makePayment(String orderId, long amount) throws StripeException;
}
