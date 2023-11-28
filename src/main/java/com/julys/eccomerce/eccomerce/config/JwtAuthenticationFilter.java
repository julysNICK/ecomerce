package com.julys.eccomerce.eccomerce.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.julys.eccomerce.eccomerce.service.JwtService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    // TODO Auto-generated method stub

    final String authorizationHeader = request.getHeader("Authorization");
    final String jwt;

    final String userName;

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      System.out.println("No se encontro el token11111111111111111111111111");
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authorizationHeader.substring(7);

    userName = jwtService.extractUsername(jwt);

    if (userName != null) {

      System.out.println("No se encontro user 11111111111111111111!!!!!!");

      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
      System.out.println("userDetails: " + userDetails);
      if (jwtService.isTokenValid(jwt, userDetails)) {

        System.out.println("wtService.isTokenValid(jwt, userDetails");

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }

    System.out.println("No se encontro en el if 11111111111111111111111111");

    filterChain.doFilter(request, response);

  }

}
