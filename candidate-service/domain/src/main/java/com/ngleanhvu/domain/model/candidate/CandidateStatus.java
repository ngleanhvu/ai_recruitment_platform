package com.ngleanhvu.domain.model.candidate;

import com.ngleanhvu.shared.exception.DomainException;
import com.ngleanhvu.shared.util.ValidationUtil;

public enum CandidateStatus {
  ACTIVE,
  INACTIVE,
  BLOCKED,
  PENDING;

  public static CandidateStatus from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new DomainException("CandidateStatus cannot be null or blank");
    }

    try {
      return CandidateStatus.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new DomainException("Invalid CandidateStatus: " + value);
    }
  }
}
