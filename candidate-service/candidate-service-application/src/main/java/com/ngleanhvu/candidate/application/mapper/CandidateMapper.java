package com.ngleanhvu.candidate.application.mapper;

import com.ngleanhvu.application.dto.request.*;
import com.ngleanhvu.candidate.application.dto.request.*;
import com.ngleanhvu.candidate.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.candidate.domain.model.candidate.*;
import org.springframework.stereotype.Component;

@Component
public final class CandidateMapper {
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

  public Skill toSkill(UpdateSkillRequest request) {
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

  public Experience toExperience(UpdateExperiencesRequest request) {
    return Experience.builder()
        .company(request.company())
        .isCurrent(request.isCurrent())
        .startDate(request.startDate())
        .endDate(request.endDate())
        .description(request.description())
        .position(request.position())
        .build();
  }

  public Education toEducation(UpdateEducationRequest request) {
    return Education.builder()
        .gpa(request.gpa())
        .major(request.major())
        .degree(request.degree())
        .school(request.school())
        .startYear(request.startYear())
        .endYear(request.endYear())
        .build();
  }

  public SocialLink toSocialLink(UpdateSocialLinkRequest request) {
    return SocialLink.builder().type(request.type()).url(request.url()).build();
  }
}
