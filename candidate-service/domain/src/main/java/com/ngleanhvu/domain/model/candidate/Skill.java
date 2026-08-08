package com.ngleanhvu.domain.model.candidate;

import com.ngleanhvu.shared.exception.ValidationException;
import lombok.Builder;

@Builder
public record Skill (
        String name,
        String level,
        int yearOrExperiences
) {
    public Skill {
        if (yearOrExperiences <= 0)
            throw new ValidationException("Year of experience must be > 0");
    }
}
