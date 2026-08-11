package com.ngleanhvu.job.domain.model.job;

import java.util.List;

public record JobRequirements(
    List<SkillRequirement> skills,
    ExperienceRequirement experience,
    EducationRequirement education,
    List<LanguageRequirement> languages) {}
