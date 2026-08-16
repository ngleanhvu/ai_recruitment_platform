package com.ngleanhvu.candidate.domain.resume;

import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.resume.enums.ResumeStatus;
import lombok.Getter;

@Getter
public final class Resume {
  private final ResumeId id;
  private final CandidateId candidateId;
  private final Integer version;
  private final ResumeFile resumeFile;
  private final ResumeStatus status;

  public Resume(
      ResumeId id,
      CandidateId candidateId,
      Integer version,
      ResumeFile resumeFile,
      ResumeStatus status) {
    this.id = id;
    this.candidateId = candidateId;
    this.version = version;
    this.resumeFile = resumeFile;
    this.status = status;
  }

  public Resume create(CandidateId candidateId, Integer version, ResumeFile resumeFile) {
    return new Resume(ResumeId.generate(), candidateId, version, resumeFile, ResumeStatus.ACTIVE);
  }

  public Resume deactivate() {
    return new Resume(id, candidateId, version, resumeFile, ResumeStatus.INACTIVE);
  }
}
