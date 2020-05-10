package com.wildadventures.msreservations.services;

import com.wildadventures.msreservations.dao.entity.Order;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

public interface ChargeService {
    Charge createCharge(Order order, String cardToken) throws StripeException;
}
