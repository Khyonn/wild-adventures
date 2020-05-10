package com.wildadventures.msreservations.controller;

import com.wildadventures.msreservations.controller.dto.ReservationIdDto;
import com.wildadventures.msreservations.controller.dto.ReservationInfoDto;
import com.wildadventures.msreservations.controller.dto.ReservationLightDto;
import com.wildadventures.msreservations.controller.dto.mapper.ReservationLightMapper;
import com.wildadventures.msreservations.dao.entity.Reservation;
import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import com.wildadventures.msreservations.dao.repository.ReservationRepository;
import com.wildadventures.msreservations.exceptions.BusinessException;
import com.wildadventures.msreservations.exceptions.ReservationAbstractException;
import com.wildadventures.msreservations.services.ReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationLightMapper reservationLightMapper;


    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("/user-reservations")
    @ApiOperation(value = "Returns the reservations of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Server retrieved user's reservations"),
            @ApiResponse(code = 204, message = "Server didn't retrieve any user's reservations", response = Void.class)
    })
    public ResponseEntity<List<ReservationLightDto>> getUserReservationList(Authentication authentication){
        List<ReservationLightDto> reservations = reservationService.getUserReservations(authentication.getName())
                .stream().map(reservationLightMapper::fromEntityToDto).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(reservations)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/adventures/{adventureId}/reservations/info")
    @ApiOperation(value = "Returns the reservations info of an adventure")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Server retrieved adventure's reservations"),
    })
    public ResponseEntity<ReservationInfoDto> getReservationInfoForAdventure(@PathVariable int adventureId, Authentication authentication){
        ReservationInfoDto toReturn = reservationService.getAdventureReservationsInformations(
                adventureId,
                authentication != null ? authentication.getName() : null
        );
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('user')")
    @DeleteMapping("/adventures/{adventureId}/reservations")
    @ApiOperation(value = "Delete reservation")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Reservation has been deleted"),
            @ApiResponse(code = 409, message = "Reservation doesn't exist")
    })
    public ResponseEntity<Void> cancelReservation(@PathVariable("adventureId") Integer adventureId, Authentication authentication) throws BusinessException {
        ReservationPK id = new ReservationPK();

        id.setAdventureId(adventureId);
        id.setUserId(authentication.getName());
        reservationService.deleteReservation(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasAnyRole('user')")
    @PutMapping("/adventures/{adventureId}/reservations")
    @ApiOperation(value = "Update reservation")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Reservation has been updated"),
            @ApiResponse(code = 409, message = "Reservation doesn't exist")
    })
    public ResponseEntity<ReservationLightDto> updateReservation(@PathVariable int adventureId, Authentication authentication, @RequestBody ReservationLightDto reservationDto) throws BusinessException {
        reservationDto.setId(new ReservationIdDto());
        reservationDto.getId().setAdventureId(adventureId);
        reservationDto.getId().setUserId(authentication.getName());
        reservationDto.setPayed(false);
        reservationDto.setReservationDate(LocalDateTime.now());

        Reservation reservation = reservationLightMapper.fromDtoToEntity(reservationDto);
        // Si le nombre de réservations tombe à zéro, on considère que c'est une suppression
        if (reservation.getReservationsNb() == 0) {
            reservationService.deleteReservation(reservation.getId());
            return new ResponseEntity<ReservationLightDto>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<ReservationLightDto>(
                reservationLightMapper.fromEntityToDto(reservationService.updateReservation(reservation)),
                HttpStatus.ACCEPTED
        );
    }

    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("/adventures/{adventureId}/reservations")
    @ApiOperation(value = "Create reservation")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Reservation has been created")
    })
    public ResponseEntity<ReservationLightDto> createReservation(@PathVariable int adventureId, Authentication authentication, @RequestBody ReservationLightDto reservationDto) throws ReservationAbstractException {
        Reservation reservation = new Reservation();
        reservation.setId(new ReservationPK());

        reservation.getId().setAdventureId(adventureId);
        reservation.getId().setUserId(authentication.getName());
        reservation.setReservationsNb(reservationDto.getReservationsNb());
        reservation.setReservationDate(LocalDateTime.now());
        reservation.setPayed(false);
        return new ResponseEntity<>(
                reservationLightMapper.fromEntityToDto(reservationService.createReservation(reservation)),
                HttpStatus.CREATED
        );
    }

}
