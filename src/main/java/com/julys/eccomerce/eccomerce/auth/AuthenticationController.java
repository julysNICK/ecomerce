package com.julys.eccomerce.eccomerce.auth;

import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.SQLErrorCodes;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.User;
import com.julys.eccomerce.eccomerce.response.UserErrorResponse;

import com.julys.eccomerce.eccomerce.response.UserResponse;
import com.julys.eccomerce.eccomerce.service.JwtService;
import com.julys.eccomerce.eccomerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  @Autowired
  private UserService userService;

  private final PasswordEncoder passwordEncoder111;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  private final UserErrorResponse userErrorResponse;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {
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

        System.out.println("linha 83 cause.getMessage(): " + cause.getMessage());

        if (cause.getMessage().contains("users_email_key")) {
          userErrorResponse.setMessage("Email já cadastrado");
          return ResponseEntity.badRequest().body(userErrorResponse.createJson());
        } else if (cause.getMessage().contains("users_username_key")) {
          userErrorResponse.setMessage("Nome de usuário já cadastrado");
          return ResponseEntity.badRequest().body(userErrorResponse.createJson());
        }

        return ResponseEntity.badRequest().body("Erro ao cadastrar");
      }
      return ResponseEntity.badRequest().body("Erro ao cadastrar");
    } catch (Exception e) {
      e.printStackTrace();

      userErrorResponse.setMessage("Erro ao cadastrar");

      return ResponseEntity.badRequest().body(userErrorResponse.createJson());

    }

  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AutheticationRequest user) {
    UserResponse userResponse = new UserResponse();
    System.out.println("login");
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