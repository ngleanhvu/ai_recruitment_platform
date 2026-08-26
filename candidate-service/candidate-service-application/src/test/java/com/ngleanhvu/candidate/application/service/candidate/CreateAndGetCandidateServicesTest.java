package com.ngleanhvu.candidate.application.service.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngleanhvu.candidate.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.candidate.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.common.exception.ResourceAlreadyExistException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateAndGetCandidateServicesTest {

  private static final CandidateId CANDIDATE_ID = new CandidateId("candidate-1");

  @Mock private CandidateRepository candidateRepository;
  @Mock private CandidateMapper candidateMapper;

  @Test
  void createMapsAndSavesNewCandidate() {
    CreateCandidateRequest request = request();
    Candidate candidate = candidate();
    when(candidateRepository.existByEmail(new Email(request.email()))).thenReturn(false);
    when(candidateMapper.toDomain(request)).thenReturn(candidate);

    new CreateCandidateService(candidateRepository, candidateMapper).execute(request);

    verify(candidateRepository).save(candidate);
  }

  @Test
  void createRejectsDuplicateEmailWithoutMappingOrSaving() {
    CreateCandidateRequest request = request();
    when(candidateRepository.existByEmail(new Email(request.email()))).thenReturn(true);

    assertThrows(
        ResourceAlreadyExistException.class,
        () -> new CreateCandidateService(candidateRepository, candidateMapper).execute(request));

    verify(candidateMapper, never()).toDomain(request);
    verify(candidateRepository, never()).save(org.mockito.ArgumentMatchers.any());
  }

  @Test
  void getDetailMapsStoredCandidate() {
    Candidate candidate = candidate();
    CandidateDetailResponse response =
        CandidateDetailResponse.builder().id(CANDIDATE_ID.value()).build();
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.of(candidate));
    when(candidateMapper.toDetail(candidate)).thenReturn(response);

    CandidateDetailResponse result =
        new GetCandidateDetailService(candidateRepository, candidateMapper).execute(CANDIDATE_ID);

    assertSame(response, result);
  }

  @Test
  void getDetailRejectsUnknownCandidate() {
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.empty());

    ResourceNotFoundException exception =
        assertThrows(
            ResourceNotFoundException.class,
            () ->
                new GetCandidateDetailService(candidateRepository, candidateMapper)
                    .execute(CANDIDATE_ID));

    assertEquals("Candidate not found", exception.getMessage());
    verify(candidateMapper, never()).toDetail(org.mockito.ArgumentMatchers.any());
  }

  private static CreateCandidateRequest request() {
    return CreateCandidateRequest.builder()
        .firstName("An")
        .lastName("Nguyen")
        .email("candidate@example.com")
        .phone("0912345678")
        .summary("Summary")
        .build();
  }

  private static Candidate candidate() {
    return Candidate.create(
        "user-1", "candidate@example.com", "An", "Nguyen", "0912345678", "Summary");
  }
}
