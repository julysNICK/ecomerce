package com.julys.eccomerce.eccomerce.service.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.julys.eccomerce.eccomerce.auth.AutheticationRequest;
import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.response.user.UserErrorResponse;
import com.julys.eccomerce.eccomerce.response.user.UserResponse;
import com.julys.eccomerce.eccomerce.service.JwtService;
import com.julys.eccomerce.eccomerce.service.user.UserService;

@Component
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder111;
  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserErrorResponse userErrorResponse;

  @Override
  public ResponseEntity<?> register(User user) {

    try {
      UserResponse userResponse = new UserResponse();
      user.setPassword(passwordEncoder111.encode(user.getPassword()));
      user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
      userService.createUser(user);
      var jwtToken = jwtService.generateToken(user);
      userResponse.setName(user.getUsername());
      userResponse.setEmail(user.getEmail());
      userResponse.setJwt(jwtToken);
      userResponse.setHttpStatus(org.springframework.http.HttpStatus.CREATED);
      return ResponseEntity.ok(userResponse.createJson());
    } catch (DataIntegrityViolationException e) {
      Throwable cause = e.getCause();
      if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
        if (cause.getMessage().contains("users_email_key")) {
          userErrorResponse.setMessage("Email já cadastrado");
          return ResponseEntity.badRequest().body(userErrorResponse.createJson());
        } else if (cause.getMessage().contains("users_username_key")) {
          userErrorResponse.setMessage("Nome de usuário já cadastrado");
          return ResponseEntity.badRequest().body(userErrorResponse.createJson());
        }
        return ResponseEntity.badRequest().body(userErrorResponse.createJson());
      }
      return ResponseEntity.badRequest().body(userErrorResponse.createJson());
    } catch (Exception e) {
      e.printStackTrace();
      userErrorResponse.setMessage("Erro ao cadastrar");
      return ResponseEntity.badRequest().body(userErrorResponse.createJson());
    }
  }

  @Override
  public ResponseEntity<?> login(AutheticationRequest user) {
    UserResponse userResponse = new UserResponse();

    try {

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

      User loggedInUser = userService.findByEmail(user.getEmail());

      userResponse.setName(loggedInUser.getUsername());
      userResponse.setEmail(loggedInUser.getEmail());
      userResponse.setJwt(jwtService.generateToken(loggedInUser));
      userResponse.setHttpStatus(org.springframework.http.HttpStatus.OK);

      return ResponseEntity.ok(userResponse.createJson());

    } catch (Exception e) {

      userErrorResponse.setMessage("Invalid email or password");

      return ResponseEntity.badRequest().body(userErrorResponse.createJson());
    }
  }

}