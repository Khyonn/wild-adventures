package com.wildadventures.msreservations.services;

import com.wildadventures.msreservations.dao.entity.Order;
import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import com.wildadventures.msreservations.exceptions.ReservationAbstractException;

public interface OrderService {
    Order createOrder(ReservationPK reservationId, String cardToken) throws ReservationAbstractException;
}
