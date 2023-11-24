package com.julys.eccomerce.eccomerce.bd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julys.eccomerce.eccomerce.entity.Order;

public interface OrderSql extends JpaRepository<Order, Long> {

}