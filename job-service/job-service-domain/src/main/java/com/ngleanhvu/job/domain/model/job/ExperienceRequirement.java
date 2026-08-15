package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public record ExperienceRequirement(Integer minimumYears, Integer maximumYears) {
  public ExperienceRequirement {
    if (minimumYears < 0)
      throw new ValidationException("Minimum experience cannot be negative");
    if (!ValidationUtil.isNull(maximumYears) && maximumYears < minimumYears)
      throw new ValidationException("Maximum experience must be greater than minimum");
  }
}
