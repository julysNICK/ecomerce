package com.julys.eccomerce.eccomerce.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

public class ErrorBuilder {
  public ResponseEntity buildResponseEntity(List<String> errors, HttpStatus status) {

    if (errors == null) {

      ErrorFormat error = new ErrorFormat(HttpStatus.BAD_REQUEST, "Errors is required");

      return ResponseEntity.badRequest().body(error.createError());
    }

    ErrorFormat error = new ErrorFormat(status, errors.get(0));

    return ResponseEntity.badRequest().body(error.createError());
  }
}
