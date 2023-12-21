package com.julys.eccomerce.eccomerce.service.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.dao.order.OrderDAO;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.request.order.RequestCreateOrder;
import com.julys.eccomerce.eccomerce.response.order.OrderResponse;
import com.julys.eccomerce.eccomerce.service.user.UserService;

@Component

public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderDAO orderDAO;

  @Autowired
  private UserService userService;

  @Override

  public ResponseEntity<?> createOrder(RequestCreateOrder order) {
    try {

      OrderResponse orderResponse = new OrderResponse();

      String format = "yyyy-MM-dd HH:mm:ss";

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

      LocalDateTime dateOrder = LocalDateTime.parse(order.getDateOrder(), formatter);

      Order orderToCreate = new Order();

      User user = userService.findById(order.getUserOrderId());

      orderToCreate.setDateOrder(dateOrder);

      orderToCreate.setStatusOrder(order.getStatusOrder());

      orderToCreate.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

      orderToCreate.setPriceTotal(order.getPriceTotal());
      orderToCreate.setUserOrder(user);

      Order orderSaved = orderDAO.createOrder(orderToCreate);

      orderResponse.setMessage("Order created successfully");
      orderResponse.setId(orderSaved.getId());
      orderResponse.setDateOrder(orderSaved.getDateOrder().toString());
      orderResponse.setStatusOrder(orderSaved.getStatusOrder());
      orderResponse.setTotalOrder(orderSaved.getPriceTotal());
      orderResponse.setUserEmail(orderSaved.getUserOrder().getEmail());
      return ResponseEntity.ok(orderResponse.createJson());
    } catch (InvalidDataAccessApiUsageException e) {

      return ResponseEntity.badRequest().body("Error creating order user id not found");

    } catch (NullPointerException e) {

      return ResponseEntity.badRequest().body("Error creating order dateOrder is required");

    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @Override
  public ResponseEntity<?> deleteOrder(Long id) {
    // return orderDAO.deleteOrder(id);
    try {
      return ResponseEntity.ok(orderDAO.deleteOrder(id));
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> findById(Long id) {

    try {

      OrderResponse orderResponse = new OrderResponse();

      Order order = orderDAO.findById(id);

      orderResponse.setMessage("Order found successfully");
      orderResponse.setId(order.getId());
      orderResponse.setDateOrder(order.getDateOrder().toString());
      orderResponse.setStatusOrder(order.getStatusOrder());
      orderResponse.setTotalOrder(order.getPriceTotal());
      orderResponse.setUserEmail(order.getUserOrder().getEmail());

      return ResponseEntity.ok(orderResponse.createJson());
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> updateOrder(Long id, Order order) {
    try {
      OrderResponse orderResponse = new OrderResponse();
      Order orderUpdated = orderDAO.updateOrder(order.getId(), order);

      orderResponse.setMessage("Order updated successfully");
      orderResponse.setId(orderUpdated.getId());
      orderResponse.setDateOrder(orderUpdated.getDateOrder().toString());
      orderResponse.setStatusOrder(orderUpdated.getStatusOrder());
      orderResponse.setTotalOrder(orderUpdated.getPriceTotal());
      orderResponse.setUserEmail(orderUpdated.getUserOrder().getEmail());
      return ResponseEntity.ok(orderResponse.createJson());

    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @Override
  public ResponseEntity<?> findOrderByUserId(Long id) {
    try {
      return ResponseEntity.ok(orderDAO.findOrderByUserIdOrder(id));
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}