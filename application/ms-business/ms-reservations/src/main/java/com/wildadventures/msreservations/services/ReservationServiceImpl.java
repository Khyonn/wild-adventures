package com.wildadventures.msreservations.services;

import com.wildadventures.msreservations.beans.AdventureBean;
import com.wildadventures.msreservations.controller.dto.ReservationInfoDto;
import com.wildadventures.msreservations.controller.dto.UserReservationInfoDto;
import com.wildadventures.msreservations.dao.entity.Reservation;
import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import com.wildadventures.msreservations.dao.repository.ReservationRepository;
import com.wildadventures.msreservations.exceptions.BusinessException;
import com.wildadventures.msreservations.exceptions.ReservationAbstractException;
import com.wildadventures.msreservations.exceptions.TechnicalException;
import com.wildadventures.msreservations.proxies.MsAventuresProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Value("${wildadventures.reservations.business.reservationLimitDays}")
    private int reservationLimitDays;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    MsAventuresProxy msAventuresProxy;

    @Override
    public List<Reservation> getUserReservations(String userId){
        LocalDateTime minReservationDate = LocalDateTime.now().minusDays(reservationLimitDays);

        return reservationRepository.findByIdUserId(userId).stream()
                .filter(reservation -> reservation.isPayed() || minReservationDate.isBefore(reservation.getReservationDate()))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationInfoDto getAdventureReservationsInformations(int adventureId, String userId) {
        ReservationInfoDto infos = new ReservationInfoDto();
        ReservationPK id = new ReservationPK();
        id.setAdventureId(adventureId);
        id.setUserId(userId);

        LocalDateTime minReservationDate = LocalDateTime.now().minusDays(reservationLimitDays);
        Reservation userReservation = reservationRepository.findById(id).orElse(null);
        infos.setActualReservationNumber(
                reservationRepository.findByIdAdventureId(adventureId).stream()
                    // On ne garde que les reservation payées ou qui n'ont pas la date dépassée
                    .filter(reservation -> {
                        return reservation.getReservationsNb() > 0 &&
                                (reservation.isPayed() || minReservationDate.isBefore(reservation.getReservationDate()));
                    })
                    // On cumule le nombre de participant
                    .map(reservation -> reservation.getReservationsNb())
                    .reduce(0, (total, participantNb) -> total + participantNb)
        );
        if (userReservation != null) {
            UserReservationInfoDto userReservationInfo = new UserReservationInfoDto();
            userReservationInfo.setPayed(userReservation.isPayed());
            userReservationInfo.setReservationsNb(userReservation.getReservationsNb());
            infos.setCurrentUserReservationInfo(userReservationInfo);
        }
        return infos;
    }

    @Transactional
    private void prepareContext(){
        // On supprime les réservation qui n'ont pas été payée et dont la date de reservation est dépassée
        reservationRepository.deleteByReservationDateBeforeAndIsPayedIsFalse(LocalDateTime.now().minusDays(reservationLimitDays));
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) throws ReservationAbstractException {
        prepareContext();
        checkCreateReservationContext(reservation);
        return reservationRepository.save(reservation);
    }
    private void checkCreateReservationContext(Reservation reservation) throws ReservationAbstractException {
        AdventureBean adventure = msAventuresProxy.getOneAdventure(reservation.getId().getAdventureId());
        int reservationsNb = reservationRepository.findByIdAdventureId(reservation.getId().getAdventureId())
                .stream().map(r -> r.getReservationsNb()).filter(participantNb -> participantNb > 0)
                .reduce(0, (accumulator, participantNb) -> accumulator + participantNb);

        // On vérifie que la réservation n'existe pas déjà
        if (reservationRepository.existsById(reservation.getId())){
            throw new TechnicalException("Reservation already exists");
        }
        // On vérifie que l'aventure existe
        if (adventure == null) {
            throw new BusinessException("Adventure doesn't exists");
        }
        // On vérifie qu'il y a suffisamment de place
        if (reservationsNb + reservation.getReservationsNb() > adventure.getMaxParticipantNumber()) {
            throw new BusinessException("There's not enough place for this adventure");
        }
        // On vérifie que l'aventure n'est pas déjà passée
        if (adventure.getStartDate().isBefore(LocalDateTime.now())){
            throw new BusinessException("The adventure is over");
        }
    }

    @Override
    @Transactional
    public Reservation updateReservation(Reservation reservation) throws BusinessException {
        prepareContext();
        checkReservationUpdateContext(reservation);
        return reservationRepository.save(reservation);
    }
    private void checkReservationUpdateContext(Reservation reservation) throws BusinessException {
        AdventureBean adventure = msAventuresProxy.getOneAdventure(reservation.getId().getAdventureId());
        Reservation dbReservation = reservationRepository.findById(reservation.getId()).orElse(null);
        int reservationsNb = reservationRepository.findByIdAdventureId(reservation.getId().getAdventureId())
                .stream()
                .filter(r -> r.getId().getUserId() != reservation.getId().getUserId() && r.getReservationsNb() > 0)
                .map(r -> r.getReservationsNb())
                .reduce(0, (total, participantNb) -> total + participantNb);

        // On vérifie qu'il y a suffisamment de place
        if (adventure == null || adventure.getMaxParticipantNumber() < reservationsNb + reservation.getReservationsNb()) {
            throw new BusinessException("There's not enough place for this adventure");
        }
        // On vérifie que la réservation n'a pas déjà été payée
        if (dbReservation == null || dbReservation.isPayed()){
            throw new BusinessException("Reservation has been payed");
        }
        // On vérifie que l'aventure n'est pas déjà passée
        if (adventure.getStartDate().isBefore(LocalDateTime.now())){
            throw new BusinessException("The adventure is over");
        }
    }

    @Override
    @Transactional
    public void deleteReservation(ReservationPK reservationId) throws BusinessException {
        Reservation dbReservation = reservationRepository.findById(reservationId).orElse(null);

        if (dbReservation != null &&
            !dbReservation.isPayed()) {
            reservationRepository.deleteById(reservationId);
        } else {
            throw new BusinessException("Reservation has been payed");
        }
    }
}
