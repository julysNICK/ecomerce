package com.julys.eccomerce.eccomerce.response.user;

import java.util.HashMap;
import java.util.Map;
import java.lang.String;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class UserResponse {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  private String jwt;

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  private HttpStatus httpStatus;

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(HttpStatus httpStatus) {
    this.httpStatus = httpStatus;
  }

  public Map<String, Object> createJson() {
    Map<String, Object> map = new HashMap<>();
    Map<String, Object> user = new HashMap<>();

    user.put("name", this.name);
    user.put("email", this.email);

    map.put("jwt", this.jwt);
    map.put("user", user);
    map.put("httpStatus", this.httpStatus);
    return map;
  }

}