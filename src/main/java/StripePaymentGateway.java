package com.example.paymentservice.paymentgateways;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static ch.qos.logback.classic.spi.ThrowableProxyVO.build;


@Component
@Primary
public class StripePaymentGateway implements PaymentGateway {

    @Value("${stripe.key}")
    private String apiKey;
    @Override
    public String initiatePayment(Long orderId) throws StripeException {

        Stripe.apiKey=apiKey;


        ProductCreateParams productCreateParams =
                ProductCreateParams.builder().setName("Water Bottle").build();
        Product product = Product.create(productCreateParams);

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(52000L)
                        .setProduct(product.getId().toString())
                        .build();

        Price price = Price.create(priceCreateParams);


        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId().toString())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("https://flipkart.com")
                                                        .build()
                                        )
                                        .build()
                        )


                        .build();

        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);

        return paymentLink.getUrl();
    }
}

