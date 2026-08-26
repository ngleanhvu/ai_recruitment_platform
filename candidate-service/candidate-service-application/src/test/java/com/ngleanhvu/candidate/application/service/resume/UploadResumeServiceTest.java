package com.ngleanhvu.candidate.application.service.resume;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.application.port.output.resume.ResumeRepository;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.resume.Resume;
import com.ngleanhvu.candidate.domain.resume.enums.ResumeStatus;
import com.ngleanhvu.common.exception.FileStorageException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.storage.FileStorage;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
class UploadResumeServiceTest {

  private static final CandidateId CANDIDATE_ID = new CandidateId("candidate-1");

  @Mock private ResumeRepository resumeRepository;
  @Mock private CandidateRepository candidateRepository;
  @Mock private FileStorage fileStorage;

  @Test
  void uploadsAndSavesNextResumeVersion() {
    MockMultipartFile file =
        new MockMultipartFile(
            "file", "candidate-resume.PDF", "application/pdf", new byte[] {1, 2, 3});
    when(candidateRepository.existById(CANDIDATE_ID)).thenReturn(true);
    when(resumeRepository.getNextVersion(CANDIDATE_ID)).thenReturn(3);

    new UploadResumeService(resumeRepository, candidateRepository, fileStorage)
        .execute(CANDIDATE_ID, file);

    ArgumentCaptor<Resume> resumeCaptor = ArgumentCaptor.forClass(Resume.class);
    verify(resumeRepository).save(resumeCaptor.capture());
    Resume resume = resumeCaptor.getValue();
    assertEquals(3, resume.getVersion());
    assertEquals("candidate-resume", resume.getResumeFile().fileName());
    assertEquals(ResumeStatus.ACTIVE, resume.getStatus());
  }

  @Test
  void rejectsResumeForUnknownCandidate() {
    MockMultipartFile file =
        new MockMultipartFile("file", "resume.pdf", "application/pdf", new byte[] {1});
    when(candidateRepository.existById(CANDIDATE_ID)).thenReturn(false);

    assertThrows(
        ResourceNotFoundException.class,
        () ->
            new UploadResumeService(resumeRepository, candidateRepository, fileStorage)
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
    MultipartFile file = org.mockito.Mockito.mock(MultipartFile.class);
    when(candidateRepository.existById(CANDIDATE_ID)).thenReturn(true);
    when(file.getOriginalFilename()).thenReturn("resume.pdf");
    when(file.getInputStream()).thenThrow(new IOException("read failed"));

    assertThrows(
        FileStorageException.class,
        () ->
            new UploadResumeService(resumeRepository, candidateRepository, fileStorage)
                .execute(CANDIDATE_ID, file));

    ArgumentCaptor<String> keyCaptor = ArgumentCaptor.forClass(String.class);
    verify(fileStorage).delete(keyCaptor.capture());
    assertEquals(true, keyCaptor.getValue().contains("/resume/"));
    verify(resumeRepository, never()).save(org.mockito.ArgumentMatchers.any());
  }
}
