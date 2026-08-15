package com.ngleanhvu.candidate.application.dto.request;

import java.util.List;

public record CreateCandidateFromResumeRequest(
    ProfileRequest profile,
    List<EducationRequest> educations,
    List<SkillRequest> skills,
    List<ExperiencesRequest> experiences,
    List<SocialLinkRequest> socialLinks) {}
