package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;
import java.util.UUID;

public record JobId(String value) {
  public JobId {
    if (ValidationUtil.isEmpty(value))
      throw new ValidationException("Job id must not be empty");
  }

  public static JobId generate() {
    return new JobId(UUID.randomUUID().toString());
  }
}
