package com.ngleanhvu.candidate.application.service.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngleanhvu.candidate.application.dto.request.ProfileRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.candidate.domain.candidate.Profile;
import com.ngleanhvu.candidate.domain.candidate.enums.CandidateStatus;
import com.ngleanhvu.common.exception.ResourceAlreadyExistException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCandidateProfileServiceTest {

  private static final CandidateId CANDIDATE_ID = new CandidateId("candidate-1");

  @Mock private CandidateRepository candidateRepository;

  private final CandidateMapper mapper = new CandidateMapper();

  @Test
  void updatesCandidateFoundByIdWhenEmailIsNew() {
    Candidate candidate = candidate(CANDIDATE_ID, "candidate@example.com", "0912345678");
    ProfileRequest request = request("updated@example.com", "0987654321");
    when(candidateRepository.findByEmail(new Email(request.email()))).thenReturn(Optional.empty());
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.of(candidate));

    new UpdateCandidateProfileService(candidateRepository, mapper).execute(CANDIDATE_ID, request);

    assertEquals(request.email(), candidate.getEmail().value());
    assertEquals(request.phone(), candidate.getProfile().phone());
    verify(candidateRepository).save(candidate);
  }

  @Test
  void rejectsEmailOwnedByAnotherCandidate() {
    Candidate another =
        candidate(new CandidateId("candidate-2"), "updated@example.com", "0912345678");
    ProfileRequest request = request("updated@example.com", "0987654321");
    when(candidateRepository.findByEmail(new Email(request.email())))
        .thenReturn(Optional.of(another));

    assertThrows(
        ResourceAlreadyExistException.class,
        () ->
            new UpdateCandidateProfileService(candidateRepository, mapper)
                .execute(CANDIDATE_ID, request));

    verify(candidateRepository, never()).save(another);
  }

  @Test
  void rejectsPhoneOwnedByAnotherCandidate() {
    Candidate candidate = candidate(CANDIDATE_ID, "updated@example.com", "0912345678");
    ProfileRequest request = request("updated@example.com", "0987654321");
    when(candidateRepository.findByEmail(new Email(request.email())))
        .thenReturn(Optional.of(candidate));
    when(candidateRepository.existByPhone(request.phone())).thenReturn(true);

    assertThrows(
        ResourceAlreadyExistException.class,
        () ->
            new UpdateCandidateProfileService(candidateRepository, mapper)
                .execute(CANDIDATE_ID, request));

    verify(candidateRepository, never()).save(candidate);
  }

  @Test
  void updatesCandidateFoundBySameEmailAndPhone() {
    Candidate candidate = candidate(CANDIDATE_ID, "candidate@example.com", "0912345678");
    ProfileRequest request = request("candidate@example.com", "0912345678");
    when(candidateRepository.findByEmail(new Email(request.email())))
        .thenReturn(Optional.of(candidate));
    when(candidateRepository.existByPhone(request.phone())).thenReturn(true);

    new UpdateCandidateProfileService(candidateRepository, mapper).execute(CANDIDATE_ID, request);

    verify(candidateRepository).save(candidate);
  }

  @Test
  void rejectsUnknownCandidate() {
    ProfileRequest request = request("updated@example.com", "0987654321");
    when(candidateRepository.findByEmail(new Email(request.email()))).thenReturn(Optional.empty());
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.empty());

    assertThrows(
        ResourceNotFoundException.class,
        () ->
            new UpdateCandidateProfileService(candidateRepository, mapper)
                .execute(CANDIDATE_ID, request));
  }

  private static ProfileRequest request(String email, String phone) {
    return new ProfileRequest("Updated", "Candidate", email, phone, "Summary");
  }

  private static Candidate candidate(CandidateId id, String email, String phone) {
    return Candidate.rehydrate(
        id,
        "user-1",
        new Email(email),
        new Profile("An", "Nguyen", phone, ""),
        null,
        CandidateStatus.ACTIVE,
        "Summary",
        null,
        null,
        null,
        null);
  }
}
