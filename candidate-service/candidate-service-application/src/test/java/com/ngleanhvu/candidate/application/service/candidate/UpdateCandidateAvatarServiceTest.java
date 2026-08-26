package com.ngleanhvu.candidate.application.service.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.candidate.domain.candidate.Profile;
import com.ngleanhvu.candidate.domain.candidate.enums.CandidateStatus;
import com.ngleanhvu.common.exception.FileStorageException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.storage.FileStorage;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class UpdateCandidateAvatarServiceTest {

  private static final CandidateId CANDIDATE_ID = new CandidateId("candidate-1");

  @Mock private CandidateRepository candidateRepository;
  @Mock private FileStorage fileStorage;

  @Test
  void uploadsNewAvatarSavesCandidateAndDeletesOldAvatar() {
    Candidate candidate = candidate("old-avatar.png");
    MockMultipartFile file =
        new MockMultipartFile("file", "avatar.PNG", "image/png", new byte[] {1, 2, 3});
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.of(candidate));

    new UpdateCandidateAvatarService(candidateRepository, fileStorage).execute(CANDIDATE_ID, file);

    assertEquals(true, candidate.getProfile().avatarKey().endsWith(".png"));
    verify(candidateRepository).save(candidate);
    verify(fileStorage).delete("old-avatar.png");
  }

  @Test
  void keepsStorageUntouchedWhenCandidateDoesNotExist() {
    MockMultipartFile file =
        new MockMultipartFile("file", "avatar.png", "image/png", new byte[] {1});
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.empty());

    assertThrows(
        ResourceNotFoundException.class,
        () ->
            new UpdateCandidateAvatarService(candidateRepository, fileStorage)
                .execute(CANDIDATE_ID, file));

    verify(fileStorage, never())
        .upload(
            anyString(),
            org.mockito.ArgumentMatchers.any(),
            org.mockito.ArgumentMatchers.anyLong(),
            anyString());
  }

  @Test
  void deletesNewObjectAndMapsInputStreamFailure() throws IOException {
    Candidate candidate = candidate(null);
    MultipartFile file = org.mockito.Mockito.mock(MultipartFile.class);
    when(candidateRepository.findById(CANDIDATE_ID)).thenReturn(Optional.of(candidate));
    when(file.isEmpty()).thenReturn(false);
    when(file.getContentType()).thenReturn("image/png");
    when(file.getSize()).thenReturn(3L);
    when(file.getOriginalFilename()).thenReturn("avatar.png");
    when(file.getInputStream()).thenThrow(new IOException("read failed"));

    assertThrows(
        FileStorageException.class,
        () ->
            new UpdateCandidateAvatarService(candidateRepository, fileStorage)
                .execute(CANDIDATE_ID, file));

    ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
    verify(fileStorage).delete(keyCaptor.capture());
    assertEquals(true, keyCaptor.getValue().contains("/avatar/"));
    verify(candidateRepository, never()).save(candidate);
  }

  private static Candidate candidate(String avatarKey) {
    return Candidate.rehydrate(
        CANDIDATE_ID,
        "user-1",
        new Email("candidate@example.com"),
        new Profile("An", "Nguyen", "0912345678", avatarKey),
        null,
        CandidateStatus.ACTIVE,
        "Summary",
        null,
        null,
        null,
        null);
  }
}
