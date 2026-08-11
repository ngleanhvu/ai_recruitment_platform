package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.shared.exception.ValidationException;
import com.ngleanhvu.shared.util.ValidationUtil;

public record Benefit(String name) {

  public Benefit {
    if (ValidationUtil.isEmpty(name))
      throw new ValidationException("Benefit name must not be blank");
  }
}
