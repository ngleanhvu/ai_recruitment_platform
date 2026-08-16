package com.ngleanhvu.candidate.infra.persistence.mapper;

import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.resume.Resume;
import com.ngleanhvu.candidate.domain.resume.ResumeFile;
import com.ngleanhvu.candidate.domain.resume.ResumeId;
import com.ngleanhvu.candidate.domain.resume.enums.ResumeStatus;
import com.ngleanhvu.candidate.infra.persistence.documet.resume.ResumeDocument;
import org.springframework.stereotype.Component;

@Component
public class ResumeDocumentMapper {
  public Resume toDomain(ResumeDocument document) {
    return new Resume(
        new ResumeId(document.getId()),
        new CandidateId(document.getCandidateId()),
        document.getVersion(),
        new ResumeFile(document.getFileName(), document.getFileKey()),
        ResumeStatus.from(document.getStatus()));
  }

  public ResumeDocument toDocument(Resume domain) {
    ResumeDocument document = new ResumeDocument();
    document.setId(domain.getId().value());
    document.setCandidateId(domain.getCandidateId().value());
    document.setStatus(domain.getStatus().name());
    document.setVersion(domain.getVersion());
    document.setFileKey(domain.getResumeFile().fileKey());
    document.setFileName(domain.getResumeFile().fileName());
    return document;
  }
}
