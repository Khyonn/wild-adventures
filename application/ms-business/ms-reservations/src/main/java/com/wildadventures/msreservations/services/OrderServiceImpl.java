package com.wildadventures.msreservations.services;

import com.wildadventures.msreservations.beans.AdventureBean;
import com.wildadventures.msreservations.dao.entity.Order;
import com.wildadventures.msreservations.dao.entity.Reservation;
import com.wildadventures.msreservations.dao.entity.pk.OrderPK;
import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import com.wildadventures.msreservations.dao.repository.OrderRepository;
import com.wildadventures.msreservations.dao.repository.ReservationRepository;
import com.wildadventures.msreservations.exceptions.BusinessException;
import com.wildadventures.msreservations.exceptions.ReservationAbstractException;
import com.wildadventures.msreservations.exceptions.TechnicalException;
import com.wildadventures.msreservations.proxies.MsAventuresProxy;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MsAventuresProxy msAventuresProxy;

    @Autowired
    ChargeService chargeService;


    @Override
    @Transactional
    public Order createOrder(ReservationPK reservationId, String cardToken) throws ReservationAbstractException {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElse(null);
        AdventureBean adventure = msAventuresProxy.getOneAdventure(reservation.getId().getAdventureId());

        // On vérifie que la réservation et l'aventure existent
        if (reservation == null || adventure == null) {
            throw new TechnicalException("Reservation doesn't exist");
        }
        // On vérifie que la réservation n'a pas déjà été payée
        if (reservation.isPayed()){
            throw new TechnicalException("Reservation has already been payed");
        }

        // On execute le paiement avec Stripe
        Order order = generateOrder(reservation, adventure);
        try {
            Charge charge = chargeService.createCharge(order, cardToken);
            order.setStripeTransactionId(charge.getId());
        } catch (StripeException exception){
            throw new BusinessException("Seems that payment didn't work", exception);
        }
        // La réservation passe à payée
        reservation.setPayed(true);
        // On sauvegarde nos entités
        reservationRepository.save(reservation);
        return orderRepository.save(order);

    }
    private Order generateOrder(Reservation reservation, AdventureBean adventure){
        OrderPK orderId = new OrderPK();
        orderId.setAdventureId(reservation.getId().getAdventureId());
        orderId.setUserId(reservation.getId().getUserId());

        Order order = new Order();
        order.setId(orderId);
        order.setPaymentDate(LocalDateTime.now());
        order.setTotalPrice(reservation.getReservationsNb() * adventure.getPrice());
        order.setReservation(reservation);
        order.setAdventureDate(adventure.getStartDate());
        order.setAdventureName(adventure.getName());
        order.setParticipantNumber(reservation.getReservationsNb());
        return order;
    }

}
