package com.julys.eccomerce.eccomerce.service.auth;

import org.springframework.http.ResponseEntity;

import com.julys.eccomerce.eccomerce.auth.AutheticationRequest;
import com.julys.eccomerce.eccomerce.entity.User;

public interface AuthService {

  public ResponseEntity<?> register(User user);

  public ResponseEntity<?> login(AutheticationRequest user);
}