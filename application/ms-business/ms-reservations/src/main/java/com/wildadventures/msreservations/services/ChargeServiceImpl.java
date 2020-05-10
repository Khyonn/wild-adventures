package com.wildadventures.msreservations.services;

import com.wildadventures.msreservations.dao.entity.Order;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChargeServiceImpl implements ChargeService {

    @Value("${wildadventures.reservations.stripe.apiKey}")
    private String stripeKey;

    @Override
    public Charge createCharge(Order order, String cardToken) throws StripeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        Stripe.apiKey = stripeKey;

        Map<String, Object> params = new HashMap<>();
        params.put("amount",(int) order.getTotalPrice() * 100);//total price in cents (non decimal currency)
        params.put("currency", "eur");
        params.put("source", cardToken);//"tok_amex"
        params.put(
                "description",
                order.getAdventureName() + " pour " + order.getParticipantNumber() + " départ le : " + order.getAdventureDate().format(formatter)
        );
        return Charge.create(params);
    }
}
