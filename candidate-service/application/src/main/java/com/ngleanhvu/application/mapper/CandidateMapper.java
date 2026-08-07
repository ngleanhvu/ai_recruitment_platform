package com.ngleanhvu.application.mapper;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.domain.model.candidate.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {
    public Candidate toDomain(CreateCandidateRequest request) {
        return Candidate.create(
                "",
                request.email(),
                request.firstName(),
                request.lastName(),
                request.phone(),
                request.summary()
        );
    }

    public CandidateDetailResponse toDetail(Candidate candidate) {
        return CandidateDetailResponse.builder()
                .id(candidate.getId().value())
                .profile(candidate.getProfile())
                .educations(candidate.getEducations())
                .address(candidate.getAddress())
                .skills(candidate.getSkills())
                .summary(candidate.getSummary())
                .status(candidate.getStatus().name())
                .experiences(candidate.getExperiences())
                .socialLinks(candidate.getSocialLinks())
                .email(candidate.getEmail().value())
                .build();
    }

}
