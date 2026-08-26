package com.ngleanhvu.candidate.application.service.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngleanhvu.candidate.application.dto.request.CreateCandidateFromResumeRequest;
import com.ngleanhvu.candidate.application.dto.request.EducationRequest;
import com.ngleanhvu.candidate.application.dto.request.ExperiencesRequest;
import com.ngleanhvu.candidate.application.dto.request.ProfileRequest;
import com.ngleanhvu.candidate.application.dto.request.SkillRequest;
import com.ngleanhvu.candidate.application.dto.request.SocialLinkRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.common.exception.ResourceAlreadyExistException;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCandidateFromResumeServiceTest {

  @Mock private CandidateRepository candidateRepository;

  private final CandidateMapper mapper = new CandidateMapper();

  @Test
  void createsCandidateWithResumeDetails() {
    CreateCandidateFromResumeRequest request = request();
    when(candidateRepository.existByEmail(new Email(request.profile().email()))).thenReturn(false);

    new CreateCandidateFromResumeService(candidateRepository, mapper).execute(request);

    ArgumentCaptor<Candidate> candidateCaptor = ArgumentCaptor.forClass(Candidate.class);
    verify(candidateRepository).save(candidateCaptor.capture());
    Candidate candidate = candidateCaptor.getValue();
    assertEquals(request.profile().email(), candidate.getEmail().value());
    assertEquals("University", candidate.getEducations().getFirst().school());
    assertEquals("Company", candidate.getExperiences().getFirst().company());
    assertEquals("Java", candidate.getSkills().getFirst().name());
    assertEquals("github", candidate.getSocialLinks().getFirst().type());
  }

  @Test
  void rejectsDuplicateResumeEmail() {
    CreateCandidateFromResumeRequest request = request();
    when(candidateRepository.existByEmail(new Email(request.profile().email()))).thenReturn(true);

    assertThrows(
        ResourceAlreadyExistException.class,
        () -> new CreateCandidateFromResumeService(candidateRepository, mapper).execute(request));

    verify(candidateRepository, never()).save(org.mockito.ArgumentMatchers.any());
  }

  private static CreateCandidateFromResumeRequest request() {
    return new CreateCandidateFromResumeRequest(
        new ProfileRequest("An", "Nguyen", "candidate@example.com", "0912345678", ""),
        List.of(new EducationRequest("University", "CS", "BS", 3.5, 2018, 2022)),
        List.of(new SkillRequest("Java", "Senior", 5)),
        List.of(
            new ExperiencesRequest(
                "Company",
                "Engineer",
                "Work",
                LocalDate.of(2020, 1, 1),
                LocalDate.of(2022, 1, 1),
                false)),
        List.of(new SocialLinkRequest("github", "https://github.com/candidate")));
  }
}
