package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.request.order.RequestCreateOrder;
import com.julys.eccomerce.eccomerce.response.order.OrderResponse;
import com.julys.eccomerce.eccomerce.service.OrderService;
import com.julys.eccomerce.eccomerce.service.UserService;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/order")
public class OrderController {
  private OrderService orderService;
  private UserService userService;

  @Autowired
  public OrderController(OrderService orderService, UserService userService) {
    this.orderService = orderService;
    this.userService = userService;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World Order";
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) {
    try {

      OrderResponse orderResponse = new OrderResponse();

      Order order = orderService.findById(id);

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

  @PostMapping("/create")
  public ResponseEntity<?> createOrder(@RequestBody RequestCreateOrder order) {
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

      Order orderSaved = orderService.createOrder(orderToCreate);

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

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateOrder(@RequestBody Order order) {

    try {
      OrderResponse orderResponse = new OrderResponse();
      Order orderUpdated = orderService.updateOrder(order.getId(), order);

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

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(orderService.deleteOrder(id));
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<?> findOrderByUserId(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(orderService.findOrderByUserId(id));
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}