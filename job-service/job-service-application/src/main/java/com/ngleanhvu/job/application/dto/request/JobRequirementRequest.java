package com.ngleanhvu.job.application.dto.request;

import com.ngleanhvu.job.domain.model.job.ExperienceRequirement;
import com.ngleanhvu.job.domain.model.job.LanguageRequirement;

import java.util.List;

public record JobRequirementRequest (
        List<SkillRequirementRequest> skills,
        ExperienceRequirementRequest experience,
        EducationRequirementRequest education,
        List<LanguageRequirementRequest> languages
) {
}
