package com.ngleanhvu.application.dto.request;

import lombok.Builder;

@Builder
public record CreateCandidateRequest(
    String firstName, String lastName, String email, String phone, String summary) {}
