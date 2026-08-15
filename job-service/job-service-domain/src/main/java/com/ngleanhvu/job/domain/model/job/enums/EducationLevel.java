package com.ngleanhvu.job.domain.model.job.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum EducationLevel {
  HIGH_SCHOOL,
  ASSOCIATE,
  BACHELOR,
  MASTER,
  DOCTORATE;

  public static EducationLevel from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("EducationLevel cannot be null or blank");
    }
    try {
      return EducationLevel.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid EducationLevel: " + value);
    }
  }
}
