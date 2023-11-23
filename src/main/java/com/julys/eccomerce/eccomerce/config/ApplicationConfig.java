package com.julys.eccomerce.eccomerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.julys.eccomerce.eccomerce.bd.UserSql;
import lombok.RequiredArgsConstructor;

// this class is used to configure the authentication manager and the password
// encoder for the application.
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  @Autowired
  private UserSql userSql;

  @Bean
  public UserDetailsService userDetailsService() {

    return username -> userSql.findByEmail(username);
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {

    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

    provider.setUserDetailsService(userDetailsService());

    provider.setPasswordEncoder(passwordEncoder());

    return provider;

  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager AuthenticationManager(AuthenticationConfiguration config) throws Exception {

    return config.getAuthenticationManager();
  }
}