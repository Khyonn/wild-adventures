package com.wildadventures.msreservations.services;

import com.wildadventures.msreservations.controller.dto.ReservationInfoDto;
import com.wildadventures.msreservations.dao.entity.Reservation;
import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import com.wildadventures.msreservations.exceptions.BusinessException;
import com.wildadventures.msreservations.exceptions.ReservationAbstractException;

import java.util.List;

public interface ReservationService {

    List<Reservation> getUserReservations(String userId);

    ReservationInfoDto getAdventureReservationsInformations(int adventureId, String userId);

    Reservation createReservation(Reservation reservation) throws ReservationAbstractException;

    Reservation updateReservation(Reservation reservation) throws BusinessException;

    void deleteReservation(ReservationPK userId) throws BusinessException;
}
