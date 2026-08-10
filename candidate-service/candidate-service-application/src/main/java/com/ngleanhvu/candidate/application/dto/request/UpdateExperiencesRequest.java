package com.ngleanhvu.candidate.application.dto.request;

import java.time.LocalDate;

public record UpdateExperiencesRequest(
    String company,
    String position,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean isCurrent) {}
