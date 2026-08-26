package com.ngleanhvu.candidate.domain.resume;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.resume.enums.ResumeStatus;
import com.ngleanhvu.common.exception.ValidationException;
import org.junit.jupiter.api.Test;

class ResumeTest {

  @Test
  void createReturnsActiveResumeWithGeneratedId() {
    Resume factory = new Resume(null, null, null, null, null);
    CandidateId candidateId = new CandidateId("candidate-1");
    ResumeFile file = new ResumeFile("resume", "candidate/resume.pdf");

    Resume resume = factory.create(candidateId, 2, file);

    assertFalse(resume.getId().value().isBlank());
    assertEquals(candidateId, resume.getCandidateId());
    assertEquals(2, resume.getVersion());
    assertEquals(file, resume.getResumeFile());
    assertEquals(ResumeStatus.ACTIVE, resume.getStatus());
  }

  @Test
  void deactivateReturnsInactiveCopy() {
    Resume resume =
        new Resume(
            new ResumeId("resume-1"),
            new CandidateId("candidate-1"),
            1,
            new ResumeFile("resume", "candidate/resume.pdf"),
            ResumeStatus.ACTIVE);

    Resume inactive = resume.deactivate();

    assertEquals(ResumeStatus.INACTIVE, inactive.getStatus());
    assertEquals(resume.getId(), inactive.getId());
    assertEquals(ResumeStatus.ACTIVE, resume.getStatus());
  }

  @Test
  void resumeValuesRequireIdentifiersAndFileNames() {
    assertFalse(ResumeId.generate().value().isBlank());
    assertDoesNotThrow(() -> new ResumeFile("resume", "candidate/resume.pdf"));
    assertThrows(ValidationException.class, () -> new ResumeId(""));
    assertThrows(ValidationException.class, () -> new ResumeFile("", "candidate/resume.pdf"));
    assertThrows(ValidationException.class, () -> new ResumeFile("resume", ""));
  }
}
