package com.julys.eccomerce.eccomerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.julys.eccomerce.eccomerce.config.SecurityConfiguration;
import com.julys.eccomerce.eccomerce.entity.Order;
import com.julys.eccomerce.eccomerce.entity.Role;
import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.request.order.RequestCreateOrder;
import com.julys.eccomerce.eccomerce.response.order.OrderResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.time.LocalDateTime;
import com.julys.eccomerce.eccomerce.rest.OrderController;
import com.julys.eccomerce.eccomerce.service.JwtService;
import com.julys.eccomerce.eccomerce.service.order.OrderService;
import com.julys.eccomerce.eccomerce.service.user.UserService;
import java.time.format.DateTimeFormatter;

@WebMvcTest(OrderController.class)
@Import(SecurityConfiguration.class)
public class OrderControllerTest {
  @MockBean
  private OrderService orderService;

  @MockBean
  private UserService userService;

  @MockBean
  private JwtService jwtService;

  @MockBean
  private AuthenticationProvider authenticationProvider;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void test_requisition_without_jwt() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/order/hello")).andExpect(
        MockMvcResultMatchers.status().isForbidden());
  }

  @WithMockUser
  @Test
  public void test_create_order() throws Exception {

    User userCreated = createUser("julysmartins", "test123", "123456789", Role.ADMIN, 1L);

    String format = "yyyy-MM-dd HH:mm:ss";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

    LocalDateTime dateOrder = LocalDateTime.parse("2021-08-01 00:00:00", formatter);

    Order orderCreated = createOrder(1L, dateOrder, "PENDING", 100.0, userCreated);

    RequestCreateOrder requestCreateOrder = createRequestCreateOrder(orderCreated, "2021-08-01 00:00:00");

    ObjectNode orderResponse = createOrderResponse(orderCreated);

    Mockito.when(userService.findById(Mockito.anyLong())).thenReturn(userCreated);

    Mockito.doReturn(ResponseEntity.status(200).body(orderResponse)).when(orderService)
        .createOrder(Mockito.any(RequestCreateOrder.class));

    mockMvc.perform(MockMvcRequestBuilders.post("/api/order/create").contentType("application/json")
        .content(objectMapper.writeValueAsString(requestCreateOrder)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Order created successfully"));

  }

  private User createUser(String username, String email, String password, Role role, Long id) {
    User user = new User();
    user.setId(id);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(password);
    user.setRole(role);
    return user;
  }

  private Order createOrder(Long id, LocalDateTime dateOrder, String statusOrder, Double priceTotal, User userOrder) {
    Order order = new Order();
    order.setId(id);
    order.setDateOrder(dateOrder);
    order.setStatusOrder(statusOrder);
    order.setPriceTotal(priceTotal);
    order.setUserOrder(userOrder);
    return order;
  }

  private RequestCreateOrder createRequestCreateOrder(Order order, String dateOrder) {
    RequestCreateOrder requestCreateOrder = new RequestCreateOrder();
    requestCreateOrder.setDateOrder(dateOrder);
    requestCreateOrder.setStatusOrder(order.getStatusOrder());
    requestCreateOrder.setPriceTotal(order.getPriceTotal());
    requestCreateOrder.setUserOrderId(order.getUserOrder().getId());
    return requestCreateOrder;
  }

  private ObjectNode createOrderResponse(Order order) {
    OrderResponse orderResponse = new OrderResponse();
    orderResponse.setId(order.getId());
    orderResponse.setDateOrder(order.getDateOrder().toString());
    orderResponse.setStatusOrder(order.getStatusOrder());
    orderResponse.setTotalOrder(order.getPriceTotal());
    orderResponse.setUserEmail(order.getUserOrder().getEmail());
    orderResponse.setMessage("Order created successfully");
    return orderResponse.createJson();
  }

}