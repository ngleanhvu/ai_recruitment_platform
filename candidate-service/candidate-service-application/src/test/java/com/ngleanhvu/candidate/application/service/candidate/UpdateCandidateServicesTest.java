package com.ngleanhvu.candidate.application.service.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngleanhvu.candidate.application.dto.request.AddressRequest;
import com.ngleanhvu.candidate.application.dto.request.EducationRequest;
import com.ngleanhvu.candidate.application.dto.request.ExperiencesRequest;
import com.ngleanhvu.candidate.application.dto.request.SkillRequest;
import com.ngleanhvu.candidate.application.dto.request.SocialLinkRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCandidateServicesTest {

  private static final CandidateId CANDIDATE_ID = new CandidateId("candidate-1");

  @Mock private CandidateRepository candidateRepository;

  private Candidate candidate;
  private CandidateMapper mapper;

  @BeforeEach
  void setUp() {
    candidate =
        Candidate.create(
            "user-1", "candidate@example.com", "An", "Nguyen", "0912345678", "Summary");
    mapper = new CandidateMapper();
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.of(candidate));
  }

  @Test
  void updatesAddress() {
    AddressRequest request = new AddressRequest("1 Main St", "District 1", "Vietnam", "HCM");

    new UpdateCandidateAddressService(mapper, candidateRepository).execute(CANDIDATE_ID, request);

    assertEquals("HCM", candidate.getAddress().city());
    verify(candidateRepository).save(candidate);
  }

  @Test
  void updatesEducation() {
    EducationRequest request = new EducationRequest("University", "CS", "BS", 3.5, 2018, 2022);

    new UpdateCandidateEducationService(candidateRepository, mapper)
        .execute(CANDIDATE_ID, List.of(request));

    assertEquals("University", candidate.getEducations().getFirst().school());
    verify(candidateRepository).save(candidate);
  }

  @Test
  void updatesExperiences() {
    ExperiencesRequest request =
        new ExperiencesRequest(
            "Company",
            "Engineer",
            "Work",
            LocalDate.of(2020, 1, 1),
            LocalDate.of(2022, 1, 1),
            false);

    new UpdateCandidateExperiencesService(candidateRepository, mapper)
        .execute(CANDIDATE_ID, List.of(request));

    assertEquals("Company", candidate.getExperiences().getFirst().company());
    verify(candidateRepository).save(candidate);
  }

  @Test
  void updatesSkills() {
    SkillRequest request = new SkillRequest("Java", "Senior", 5);

    new UpdateCandidateSkillService(candidateRepository, mapper)
        .execute(CANDIDATE_ID, List.of(request));

    assertEquals("Java", candidate.getSkills().getFirst().name());
    verify(candidateRepository).save(candidate);
  }

  @Test
  void updatesSocialLinks() {
    SocialLinkRequest request = new SocialLinkRequest("github", "https://github.com/candidate");

    new UpdateCandidateSocialLinkService(candidateRepository, mapper)
        .execute(CANDIDATE_ID, List.of(request));

    assertEquals("github", candidate.getSocialLinks().getFirst().type());
    verify(candidateRepository).save(candidate);
  }
}
