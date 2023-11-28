package com.julys.eccomerce.eccomerce.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ProductErrorResponse {
  private int status;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, Object> createJson() {
    Map<String, Object> map = new HashMap<>();
    map.put("status", this.status);
    map.put("message", this.message);

    return map;
  }

}