package com.ngleanhvu.application.mapper;

import com.ngleanhvu.application.dto.request.AddressRequest;
import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.request.SkillRequest;
import com.ngleanhvu.application.dto.request.UpdateProfileRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.domain.model.candidate.Address;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.Profile;
import com.ngleanhvu.domain.model.candidate.Skill;
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
        request.summary());
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

  public Skill toSkill(SkillRequest request) {
    return Skill.builder()
        .name(request.name())
        .level(request.level())
        .yearOrExperiences(request.yearOrExperiences())
        .build();
  }

  public Profile toProfile(UpdateProfileRequest request) {
    return Profile.builder()
        .phone(request.phone())
        .firstName(request.firstName())
        .lastName(request.lastName())
        .build();
  }

  public Address toAddress(AddressRequest request) {
    return Address.builder()
        .address(request.address())
        .city(request.city())
        .country(request.country())
        .district(request.district())
        .build();
  }
}
