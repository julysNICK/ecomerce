package com.julys.eccomerce.eccomerce.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julys.eccomerce.eccomerce.entity.User;
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

  @PostMapping("/register")
  public AuthenticationResponse register(@RequestBody User user) {
    System.out.println("regiregiregiregiregiregister");
    try {
      user.setPassword(passwordEncoder111.encode(user.getPassword()));
      user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
      userService.createUser(user);

      var jwtToken = jwtService.generateToken(user);

      return AuthenticationResponse.builder().token(jwtToken).build();

    } catch (Exception e) {
      System.out.println("Cai no exception.");
      System.out.println(e);
      return AuthenticationResponse.builder().token(null).build();
    }
  }

  @PostMapping("/login")
  public AuthenticationResponse login(@RequestBody AutheticationRequest user) {
    try {
      // User loggedInUser = userService.findByEmail(user.getEmail());

      // boolean isMatcherPassword = verifyPassword(user, loggedInUser);

      // if (!isMatcherPassword) {
      // return AuthenticationResponse.builder().token(null).build();
      // }

      // var jwtToken = jwtService.generateToken(loggedInUser);

      // return AuthenticationResponse.builder().token(jwtToken).build();

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

      User loggedInUser = userService.findByEmail(user.getEmail());

      return AuthenticationResponse.builder().token(jwtService.generateToken(loggedInUser)).build();

    } catch (Exception e) {
      return AuthenticationResponse.builder().token(null).build();
    }
  }

}