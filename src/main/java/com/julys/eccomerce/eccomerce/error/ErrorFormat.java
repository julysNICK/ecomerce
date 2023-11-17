package com.julys.eccomerce.eccomerce.error;

import java.util.HashMap;

import org.springframework.http.HttpStatus;

public class ErrorFormat {

  private String status;

  private String message;

  public ErrorFormat(HttpStatus status, String message) {
    this.status = status.toString();
    this.message = message;
  }

  public HashMap<String, String> createError() {
    HashMap<String, String> error = new HashMap<>();

    error.put("error", message);

    error.put("status", status);

    return error;

  }

}