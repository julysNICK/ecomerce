package com.julys.eccomerce.eccomerce.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

  @PostMapping("/create")
  public ResponseEntity<?> createOrder(@RequestBody RequestCreateOrder order) {

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
  }
}