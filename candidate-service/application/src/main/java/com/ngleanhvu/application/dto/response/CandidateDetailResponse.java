package com.ngleanhvu.application.dto.response;

import com.ngleanhvu.domain.model.candidate.*;
import lombok.Builder;

import java.util.List;

@Builder
public record CandidateDetailResponse(
        String id,
        String summary,
        String email,
        String status,
        Profile profile,
        Address address,
        List<Skill> skills,
        List<Experience> experiences,
        List<Education> educations,
        List<SocialLink> socialLinks
) {
}