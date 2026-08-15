package com.ngleanhvu.job.domain.model.job.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum EmploymentType {
  FULL_TIME,
  PART_TIME,
  CONTRACT,
  INTERNSHIP,
  FREELANCE;

  public static EmploymentType from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("Employment type cannot be null or blank");
    }
    try {
      return EmploymentType.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid EmploymentType: " + value);
    }
  }
}
