package com.ngleanhvu.candidate.application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ngleanhvu.candidate.application.dto.request.AddressRequest;
import com.ngleanhvu.candidate.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.candidate.application.dto.request.EducationRequest;
import com.ngleanhvu.candidate.application.dto.request.ExperiencesRequest;
import com.ngleanhvu.candidate.application.dto.request.ProfileRequest;
import com.ngleanhvu.candidate.application.dto.request.SkillRequest;
import com.ngleanhvu.candidate.application.dto.request.SocialLinkRequest;
import com.ngleanhvu.candidate.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.candidate.domain.candidate.Address;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.Education;
import com.ngleanhvu.candidate.domain.candidate.Experience;
import com.ngleanhvu.candidate.domain.candidate.Profile;
import com.ngleanhvu.candidate.domain.candidate.Skill;
import com.ngleanhvu.candidate.domain.candidate.SocialLink;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;

class CandidateMapperTest {

  private final CandidateMapper mapper = new CandidateMapper();

  @Test
  void mapsCreateRequestToCandidate() {
    CreateCandidateRequest request =
        CreateCandidateRequest.builder()
            .firstName("An")
            .lastName("Nguyen")
            .email("candidate@example.com")
            .phone("0912345678")
            .summary("Summary")
            .build();

    Candidate candidate = mapper.toDomain(request);

    assertEquals(request.email(), candidate.getEmail().value());
    assertEquals(request.firstName(), candidate.getProfile().firstName());
    assertEquals(request.phone(), candidate.getProfile().phone());
    assertEquals(request.summary(), candidate.getSummary());
  }

  @Test
  void mapsCandidateToDetailResponse() {
    Candidate candidate =
        Candidate.create(
            "user-1", "candidate@example.com", "An", "Nguyen", "0912345678", "Summary");
    Address address = new Address("1 Main St", "District 1", "Vietnam", "HCM");
    Skill skill = new Skill("Java", "Senior", 5);
    candidate.updateAddress(address);
    candidate.updateSkills(List.of(skill));

    CandidateDetailResponse response = mapper.toDetail(candidate);

    assertEquals(candidate.getId().value(), response.id());
    assertEquals("candidate@example.com", response.email());
    assertEquals("PENDING", response.status());
    assertEquals(address, response.address());
    assertEquals(List.of(skill), response.skills());
  }

  @Test
  void mapsAllNestedRequests() {
    LocalDate start = LocalDate.of(2020, 1, 1);
    LocalDate end = LocalDate.of(2022, 1, 1);

    Profile profile =
        mapper.toProfile(
            new ProfileRequest("An", "Nguyen", "candidate@example.com", "0912345678", ""));
    Address address =
        mapper.toAddress(new AddressRequest("1 Main St", "District 1", "Vietnam", "HCM"));
    Skill skill = mapper.toSkill(new SkillRequest("Java", "Senior", 5));
    Education education =
        mapper.toEducation(new EducationRequest("University", "CS", "BS", 3.5, 2018, 2022));
    Experience experience =
        mapper.toExperience(
            new ExperiencesRequest("Company", "Engineer", "Work", start, end, false));
    SocialLink socialLink =
        mapper.toSocialLink(new SocialLinkRequest("github", "https://github.com/candidate"));

    assertEquals("An", profile.firstName());
    assertEquals("HCM", address.city());
    assertEquals("Java", skill.name());
    assertEquals("University", education.school());
    assertEquals(start, experience.startDate());
    assertEquals("github", socialLink.type());
  }
}
