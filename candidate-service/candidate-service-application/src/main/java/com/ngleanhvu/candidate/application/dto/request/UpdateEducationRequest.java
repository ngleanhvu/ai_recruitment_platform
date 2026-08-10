package com.ngleanhvu.candidate.application.dto.request;

public record UpdateEducationRequest(
    String school, String major, String degree, double gpa, int startYear, int endYear) {}
