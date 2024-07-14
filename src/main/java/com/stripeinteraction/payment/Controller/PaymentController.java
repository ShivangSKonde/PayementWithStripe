package com.stripeinteraction.payment.Controller;

import com.stripe.exception.StripeException;
import com.stripeinteraction.payment.Models.OrderInfo;
import com.stripeinteraction.payment.Services.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    public String getPaymentURL(@RequestBody OrderInfo payment) throws StripeException {
        String paymentURL=paymentService.makePayment(payment.getOrderId(),payment.getAmount());
        return paymentURL;
    }

    @PostMapping("/payment/webhook")
    public String webhook() throws StripeException {
        System.out.println("webhook Successful");
        return "";
    }


}
