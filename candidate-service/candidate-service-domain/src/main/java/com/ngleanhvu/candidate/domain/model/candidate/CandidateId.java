package com.ngleanhvu.candidate.domain.model.candidate;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;
import java.util.UUID;
import lombok.Builder;

@Builder
public record CandidateId(String value) {
  public CandidateId {
    if (ValidationUtil.isEmpty(value))
      throw new ValidationException("Candidate id must not be empty");
  }

  public static CandidateId generate() {
    return new CandidateId(UUID.randomUUID().toString());
  }
}
