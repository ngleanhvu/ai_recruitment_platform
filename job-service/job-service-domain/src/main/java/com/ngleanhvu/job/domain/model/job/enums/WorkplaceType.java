package com.ngleanhvu.job.domain.model.job.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum WorkplaceType {
  REMOTE,
  HYBRID,
  ONSITE;

  public static WorkplaceType from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("Work place type cannot be null or blank");
    }
    try {
      return WorkplaceType.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid WorkPlaceType: " + value);
    }
  }
}
