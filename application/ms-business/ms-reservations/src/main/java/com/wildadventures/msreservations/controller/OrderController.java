package com.wildadventures.msreservations.controller;

import com.wildadventures.msreservations.controller.dto.InvoiceDto;
import com.wildadventures.msreservations.controller.dto.mapper.InvoiceMapper;
import com.wildadventures.msreservations.dao.entity.Order;
import com.wildadventures.msreservations.dao.entity.pk.OrderPK;
import com.wildadventures.msreservations.dao.entity.pk.ReservationPK;
import com.wildadventures.msreservations.dao.repository.OrderRepository;
import com.wildadventures.msreservations.exceptions.ReservationAbstractException;
import com.wildadventures.msreservations.services.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    InvoiceMapper invoiceMapper;

    @Autowired
    OrderService orderService;

    @PreAuthorize("hasAnyRole('user')")
    @GetMapping("/user-reservations/{adventureId}/invoice")
    @ApiOperation(value = "Returns the reservations of an user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Server retrieved user's reservations"),
            @ApiResponse(code = 204, message = "Server didn't retrieve any user's reservations", response = Void.class)
    })
    public ResponseEntity<InvoiceDto> getUserOneInvoice(Authentication authentication, @PathVariable int adventureId){
        OrderPK id= new OrderPK();
        id.setAdventureId(adventureId);
        id.setUserId(authentication.getName());

        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            return new ResponseEntity<>(invoiceMapper.fromEntityToDto(order), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PreAuthorize("hasAnyRole('user')")
    @PostMapping("/adventures/{adventureId}/reservations/payment")
    @ApiOperation(value = "Create order and update reservation")
    @ApiResponses({
            @ApiResponse(code = 202, message = "Reservation has been updated and order has been created")
    })
    public ResponseEntity<InvoiceDto> payment(@PathVariable int adventureId, Authentication authentication, @RequestBody String cardToken) throws ReservationAbstractException {
        ReservationPK reservationId = new ReservationPK();
        reservationId.setAdventureId(adventureId);
        reservationId.setUserId(authentication.getName());

        return new ResponseEntity<>(invoiceMapper.fromEntityToDto(
                orderService.createOrder(reservationId, cardToken)
        ), HttpStatus.CREATED);
    }

}
