package com.wildadventures.msreservations.dao.repository;

import com.wildadventures.msreservations.dao.entity.Order;
import com.wildadventures.msreservations.dao.entity.pk.OrderPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderPK> {
    
}
