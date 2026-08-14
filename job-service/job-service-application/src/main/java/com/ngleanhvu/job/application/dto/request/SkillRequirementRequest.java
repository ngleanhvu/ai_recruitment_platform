package com.ngleanhvu.job.application.dto.request;

public record SkillRequirementRequest (String name, boolean required, int minimumYears) {
}
