package com.ngleanhvu.candidate.domain.model.resume.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum ResumeStatus {
  INACTIVE,
  ACTIVE;

  public static ResumeStatus from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("ResumeStatus cannot be null or blank");
    }

    try {
      return ResumeStatus.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid ResumeStatus: " + value);
    }
  }
}
