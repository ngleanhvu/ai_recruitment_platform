package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.shared.exception.ValidationException;

public record ExperienceRequirement(int minimumYears, int maximumYears) {
  public ExperienceRequirement {
    if (minimumYears < 0) throw new ValidationException("Minimum experience cannot be negative");
    if (maximumYears < minimumYears)
      throw new ValidationException("Maximum experience must be greater than minimum");
  }
}
