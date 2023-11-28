package com.julys.eccomerce.eccomerce.error;

import java.lang.Throwable;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// @ControllerAdvice
// public class ErrorExeception {

// @ExceptionHandler(DataIntegrityViolationException.class)
// @ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
// public ResponseEntity<?>
// handleDataIntegrityViolationException(DataIntegrityViolationException ex) {

// Throwable cause = ex.getCause();

// if (cause instanceof PSQLException) {
// PSQLException exception = (PSQLException) cause;
// if (exception.getMessage().contains("email")) {
// return ResponseEntity.badRequest().body("Email já cadastrado");
// }

// }

// if (cause instanceof org.hibernate.exception.ConstraintViolationException) {
// org.hibernate.exception.ConstraintViolationException exception =
// (org.hibernate.exception.ConstraintViolationException) cause;
// if (exception.getMessage().contains("email")) {
// return ResponseEntity.badRequest().body("Email já cadastrado");
// }

// }

// return ResponseEntity.badRequest().body("Erro ao cadastrar");
// }

// }