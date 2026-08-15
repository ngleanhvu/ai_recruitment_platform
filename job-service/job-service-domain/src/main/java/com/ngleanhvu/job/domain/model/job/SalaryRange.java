package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.job.domain.model.job.enums.Currency;
import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;
import java.math.BigDecimal;

public record SalaryRange(BigDecimal min, BigDecimal max, Currency currency) {
  public SalaryRange {
    if (ValidationUtil.isNotNull(min) && min.compareTo(BigDecimal.ZERO) < 0)
      throw new ValidationException("Minimum salary must be positive");
    if (ValidationUtil.isNotNull(max) && max.compareTo(min) < 0)
      throw new ValidationException("Maximum salary must be greater than minimum salary");
  }
}
