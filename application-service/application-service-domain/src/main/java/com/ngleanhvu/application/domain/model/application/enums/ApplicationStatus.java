package com.ngleanhvu.application.domain.model.application.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum ApplicationStatus {
  SUBMITTED,
  SCREENING,
  SHORTLISTED,
  INTERVIEW,
  OFFERED,
  HIRED,
  REJECTED,
  WITHDRAWN;

  public static ApplicationStatus from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("Application status cannot be null or blank");
    }
    try {
      return ApplicationStatus.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid ApplicationStatus: " + value);
    }
  }
}
