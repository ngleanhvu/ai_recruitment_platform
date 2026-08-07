package com.ngleanhvu.domain.model.candidate;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CandidateId (
        String value
) {
    public static CandidateId generate() {
        return new CandidateId(UUID.randomUUID().toString());
    }
}
