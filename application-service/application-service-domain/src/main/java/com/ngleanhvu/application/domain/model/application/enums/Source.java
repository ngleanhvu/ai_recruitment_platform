package com.ngleanhvu.application.domain.model.application.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum Source {
  LINKEDIN,
  COMPANY_WEBSITE,
  FACEBOOK,
  REFERENCE;

  public static Source from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("Source cannot be null or blank");
    }
    try {
      return Source.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid Source: " + value);
    }
  }
}
