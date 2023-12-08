package com.julys.eccomerce.eccomerce.response.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class UserErrorResponse {

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, Object> createJson() {
    Map<String, Object> map = new HashMap<>();

    map.put("message", this.message);

    return map;
  }
}