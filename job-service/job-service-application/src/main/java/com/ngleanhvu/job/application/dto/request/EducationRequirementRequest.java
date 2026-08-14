package com.ngleanhvu.job.application.dto.request;

import com.ngleanhvu.job.domain.model.job.enums.EducationLevel;

public record EducationRequirementRequest (String level, boolean required) {
}
