package com.ngleanhvu.job.domain.model.job.enums;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public enum Currency {
  VND,
  USD,
  EUR;

  public static Currency from(String value) {
    if (ValidationUtil.isEmpty(value)) {
      throw new ValidationException("Currency type cannot be null or blank");
    }
    try {
      return Currency.valueOf(value.trim().toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new ValidationException("Invalid CurrencyType: " + value);
    }
  }
}
