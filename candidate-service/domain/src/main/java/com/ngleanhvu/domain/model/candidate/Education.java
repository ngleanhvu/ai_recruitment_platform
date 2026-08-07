package com.ngleanhvu.domain.model.candidate;

import com.ngleanhvu.shared.exception.ValidationException;
import lombok.Builder;

@Builder
public record Education (
        String school,
        String major,
        String degree,
        double gpa,
        int startYear,
        int endYear
) {
    public Education {
        if (gpa < 0.0 || gpa > 4.0)
            throw new ValidationException("Gpa must be  >= 0 and <= 4");
        if (startYear > endYear)
            throw new ValidationException("Start year must be before end year");
    }
}
