package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.shared.exception.ValidationException;
import com.ngleanhvu.shared.util.ValidationUtil;
import java.time.LocalDateTime;

public record ApplicationDeadline(LocalDateTime value) {

  public ApplicationDeadline {

    if (ValidationUtil.isNull(value))
      throw new ValidationException("Application deadline is required");
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(value);
  }
}
