package com.ngleanhvu.candidate.application.dto.request;

public record EducationRequest(
    String school, String major, String degree, double gpa, int startYear, int endYear) {}
