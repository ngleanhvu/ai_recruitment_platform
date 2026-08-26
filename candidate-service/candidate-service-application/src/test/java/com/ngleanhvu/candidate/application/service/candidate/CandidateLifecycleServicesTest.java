package com.ngleanhvu.candidate.application.service.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.candidate.domain.candidate.Profile;
import com.ngleanhvu.candidate.domain.candidate.enums.CandidateStatus;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CandidateLifecycleServicesTest {

  private static final CandidateId CANDIDATE_ID = new CandidateId("candidate-1");

  @Mock private CandidateRepository candidateRepository;

  private Candidate candidate;

  @BeforeEach
  void setUp() {
    candidate = candidate(CandidateStatus.PENDING);
  }

  @Test
  void activateLoadsMutatesAndSavesCandidate() {
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.of(candidate));

    new ActivateCandidateService(candidateRepository).execute(CANDIDATE_ID);

    assertEquals(CandidateStatus.ACTIVE, candidate.getStatus());
    verify(candidateRepository).save(candidate);
  }

  @Test
  void activateRejectsUnknownCandidate() {
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.empty());

    assertThrows(
        ResourceNotFoundException.class,
        () -> new ActivateCandidateService(candidateRepository).execute(CANDIDATE_ID));

    verify(candidateRepository, never()).save(candidate);
  }

  @Test
  void blockLoadsMutatesAndSavesCandidate() {
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.of(candidate));

    new BlockCandidateService(candidateRepository).execute(CANDIDATE_ID);

    assertEquals(CandidateStatus.BLOCKED, candidate.getStatus());
    verify(candidateRepository).save(candidate);
  }

  @Test
  void blockRejectsUnknownCandidate() {
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.empty());

    assertThrows(
        ResourceNotFoundException.class,
        () -> new BlockCandidateService(candidateRepository).execute(CANDIDATE_ID));

    verify(candidateRepository, never()).save(candidate);
  }

  @Test
  void existsDelegatesToRepository() {
    when(candidateRepository.existById(CANDIDATE_ID)).thenReturn(true);

    boolean exists = new ExistCandidateByIdService(candidateRepository).execute(CANDIDATE_ID);

    assertTrue(exists);
    verify(candidateRepository).existById(CANDIDATE_ID);
  }

  private static Candidate candidate(CandidateStatus status) {
    return Candidate.rehydrate(
        CANDIDATE_ID,
        "user-1",
        new Email("candidate@example.com"),
        new Profile("An", "Nguyen", "0912345678", ""),
        null,
        status,
        "Summary",
        null,
        null,
        null,
        null);
  }
}
