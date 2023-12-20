package com.julys.eccomerce.eccomerce.service.order;

import org.springframework.http.ResponseEntity;

import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.error.ErrorOrder;
import com.julys.eccomerce.eccomerce.request.order.RequestCreateOrder;
import com.julys.eccomerce.eccomerce.response.order.ListOrderWithUsers;

public interface OrderService {
  ResponseEntity<?> findById(Long id);

  ResponseEntity<?> createOrder(RequestCreateOrder order);

  ResponseEntity<?> updateOrder(Long id, Order order);

  ResponseEntity<?> deleteOrder(Long id);

  ResponseEntity<?> findOrderByUserId(Long id);
}