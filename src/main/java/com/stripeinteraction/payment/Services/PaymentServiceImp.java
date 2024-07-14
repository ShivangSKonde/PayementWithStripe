package com.stripeinteraction.payment.Services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements PaymentService{
    @Override
    public String makePayment(String orderId, long amount) throws StripeException {
        Stripe.apiKey = "sk_test_51PbdPHRv0lGLevw83wkZsQQSRp9r1RR79EnUA9HAN6zMmrK1ARMWMpcapfFV5kxqo6QTwZQBBl8DukZ2RAcFmqag006roLrIwO";

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName(orderId).build()
                        )
                        .build();

        Price price = Price.create(params);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();
        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return paymentLink.getUrl();
    }
}
