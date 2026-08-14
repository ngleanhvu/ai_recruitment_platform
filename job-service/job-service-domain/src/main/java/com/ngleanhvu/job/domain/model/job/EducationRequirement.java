package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.job.domain.model.job.enums.EducationLevel;

public record EducationRequirement(EducationLevel level, boolean required) {}
