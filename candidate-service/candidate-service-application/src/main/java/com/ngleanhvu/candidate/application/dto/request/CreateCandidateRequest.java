package com.ngleanhvu.candidate.application.dto.request;

import lombok.Builder;

@Builder
public record CreateCandidateRequest(
    String firstName, String lastName, String email, String phone, String summary) {}
