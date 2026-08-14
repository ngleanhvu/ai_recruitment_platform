package com.ngleanhvu.candidate.domain.model.resume;

import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.shared.exception.ValidationException;
import com.ngleanhvu.shared.util.ValidationUtil;

import java.util.UUID;

public record ResumeId (
        String value
) {
    public ResumeId {
        if (ValidationUtil.isEmpty(value))
            throw new ValidationException("Resume id must not be empty");
    }

    public static ResumeId generate() {
        return new ResumeId(UUID.randomUUID().toString());
    }
}
