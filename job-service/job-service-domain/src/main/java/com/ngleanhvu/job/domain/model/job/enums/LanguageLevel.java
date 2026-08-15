package com.ngleanhvu.job.domain.model.job.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum LanguageLevel {
  A1,
  A2,
  B1,
  B2,
  C1,
  C2;

  public static LanguageLevel from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("LanguageLevel cannot be null or blank");
    }
    try {
      return LanguageLevel.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid LanguageLevel: " + value);
    }
  }
}
