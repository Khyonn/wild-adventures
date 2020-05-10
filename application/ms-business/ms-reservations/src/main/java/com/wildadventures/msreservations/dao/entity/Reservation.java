package com.wildadventures.msreservations.dao.entity;

import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
@Getter
@Setter
public class Reservation {
    @EmbeddedId
    private ReservationPK id;

    @Column(name="reservations_nb")
    @NotNull
    private int reservationsNb;

    @Column(name="reservations_date")
    @NotNull
    private LocalDateTime reservationDate;

    @Column(name="isPayed")
    @NotNull
    private boolean isPayed;

}
