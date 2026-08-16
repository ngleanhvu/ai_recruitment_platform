package com.ngleanhvu.candidate.domain.candidate;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;
import lombok.Builder;

@Builder
public record Skill(String name, String level, int yearOrExperiences) {
  public Skill {
    if (yearOrExperiences <= 0) throw new ValidationException("Year of experience must be > 0");
    if (ValidationUtil.isEmpty(name)) throw new ValidationException("Name must not be empty");
    if (ValidationUtil.isEmpty(level)) throw new ValidationException("Level must not be empty");
  }
}
