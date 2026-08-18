package com.ngleanhvu.job.application.dto.request;

import java.util.List;

public record JobRequirementRequest(
    List<SkillRequirementRequest> skills,
    ExperienceRequirementRequest experience,
    EducationRequirementRequest education,
    List<LanguageRequirementRequest> languages) {}
