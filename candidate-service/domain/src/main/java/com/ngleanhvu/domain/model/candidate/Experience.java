package com.ngleanhvu.domain.model.candidate;

import com.ngleanhvu.shared.exception.ValidationException;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record Experience(
    String company,
    String position,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean isCurrent) {
  public Experience {
    if (startDate().isAfter(endDate))
      throw new ValidationException("Start date must be before end date");
  }
}
