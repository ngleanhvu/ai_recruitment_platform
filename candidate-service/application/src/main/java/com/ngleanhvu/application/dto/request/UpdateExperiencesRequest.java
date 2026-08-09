package com.ngleanhvu.application.dto.request;

import java.time.LocalDate;

public record UpdateExperiencesRequest(
    String company,
    String position,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    boolean isCurrent) {}
