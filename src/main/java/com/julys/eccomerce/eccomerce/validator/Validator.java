package com.julys.eccomerce.eccomerce.validator;

import io.micrometer.common.util.StringUtils;

/**
 * Validator
 */
public class Validator {
  public boolean validateRequiredField(Object field, String message) {
    if (field == null || (field instanceof String && StringUtils.isEmpty((String) field))) {
      return false;
    }

    return true;
  }
}