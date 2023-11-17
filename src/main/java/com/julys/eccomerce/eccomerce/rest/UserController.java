package com.julys.eccomerce.eccomerce.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello World";
  }

}