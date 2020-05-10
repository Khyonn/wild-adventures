package com.wildadventures.msreservations.dao.repository;

import com.wildadventures.msreservations.dao.entity.Reservation;
import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationPK> {
    List<Reservation> findByIdUserId(String userId);
    List<Reservation> findByIdAdventureId(int adventureId);
    void deleteByReservationDateBeforeAndIsPayedIsFalse(LocalDateTime expirationDate);
}
