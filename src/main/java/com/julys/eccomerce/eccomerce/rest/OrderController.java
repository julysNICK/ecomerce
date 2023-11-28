package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.julys.eccomerce.eccomerce.request.RequestCreateOrder;
import com.julys.eccomerce.eccomerce.service.OrderService;
import com.julys.eccomerce.eccomerce.service.UserService;
import java.time.format.DateTimeFormatter;
import io.micrometer.core.ipc.http.HttpSender.Request;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.OffsetDateTime;

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
      return ResponseEntity.ok(orderService.findById(id));
    } catch (Exception e) {

      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/create")
  public ResponseEntity<?> createOrder(@RequestBody RequestCreateOrder order) {

    try {
      String format = "yyyy-MM-dd HH:mm:ss";

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

      LocalDateTime dateOrder = LocalDateTime.parse(order.getDateOrder(), formatter);

      Order orderToCreate = new Order();

      User user = userService.findById(order.getUserOrderId());

      orderToCreate.setDateOrder(dateOrder);

      orderToCreate.setStatusOrder(order.getStatusOrder());

      orderToCreate.setUserOrderId(user);

      orderToCreate.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

      orderToCreate.setPriceTotal(order.getPriceTotal());

      return ResponseEntity.ok(orderService.createOrder(orderToCreate));
    } catch (Exception e) {
      // TODO: handle exception
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateOrder(@RequestBody Order order) {
    try {
      return ResponseEntity.ok(orderService.updateOrder(order.getId(), order));
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