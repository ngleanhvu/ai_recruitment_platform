package com.ngleanhvu.candidate.domain.model.candidate.enums;


import com.ngleanhvu.common.util.ValidationUtil;
import jakarta.validation.ValidationException;

public enum CandidateStatus {
  ACTIVE,
  INACTIVE,
  BLOCKED,
  PENDING;

  public static CandidateStatus from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("CandidateStatus cannot be null or blank");
    }

    try {
      return CandidateStatus.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid CandidateStatus: " + value);
    }
  }
}
