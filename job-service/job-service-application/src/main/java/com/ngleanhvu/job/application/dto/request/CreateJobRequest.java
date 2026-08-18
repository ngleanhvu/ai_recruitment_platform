package com.ngleanhvu.job.application.dto.request;

public record CreateJobRequest(
    String recruiterId,
    String title,
    String description,
    String employmentType,
    WorkPlaceRequest workplace) {}
