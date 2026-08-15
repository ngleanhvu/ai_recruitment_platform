package com.ngleanhvu.candidate.domain.model.candidate;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;
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
    if (ValidationUtil.isEmpty(company)) throw new ValidationException("Company must not be empty");
    if (ValidationUtil.isNull(startDate))
      throw new ValidationException("Start date must not be null");
  }
}
