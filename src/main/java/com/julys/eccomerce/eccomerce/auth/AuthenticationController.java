package com.julys.eccomerce.eccomerce.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.service.auth.AuthService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {
    return authService.register(user);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AutheticationRequest user) {
    return authService.login(user);
  }

}