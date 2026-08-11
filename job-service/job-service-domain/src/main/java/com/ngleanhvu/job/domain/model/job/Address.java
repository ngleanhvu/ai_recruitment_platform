package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.shared.exception.ValidationException;
import com.ngleanhvu.shared.util.ValidationUtil;

public record Address(String city, String country) {
  public Address {
    if (ValidationUtil.isEmpty(city)) throw new ValidationException("City must not be empty");
    if (ValidationUtil.isEmpty(country)) throw new ValidationException("Country must not be empty");
  }
}
