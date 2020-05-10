package com.wildadventures.msreservations.dao.entity;

import com.wildadventures.msreservations.dao.entity.pk.OrderPK;

import javax.persistence.EmbeddedId;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @EmbeddedId
    private OrderPK id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("reservationId")
    @NotNull
    @JoinColumns({
            @JoinColumn(
                    name = "adventure_id",
                    referencedColumnName = "adventure_id"),
            @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "user_id")
    })
    private Reservation reservation;

    @Column(name ="adventure_name")
    @NotNull
    private String adventureName;

    @Column(name ="total_price")
    @NotNull
    private double totalPrice;

    @Column(name ="adventure_date")
    @NotNull
    private LocalDateTime adventureDate;

    @Column(name ="payment_date")
    @NotNull
    private LocalDateTime paymentDate;

    @Column(name ="stripe_transaction")
    @NotNull
    private String stripeTransactionId;

    @Column(name ="participant_number")
    @NotNull
    private int participantNumber;
    

}
