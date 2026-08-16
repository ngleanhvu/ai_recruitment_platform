package com.ngleanhvu.candidate.infra.persistence.mapper;

import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.candidate.domain.candidate.enums.CandidateStatus;
import com.ngleanhvu.candidate.infra.persistence.documet.candidate.CandidateDocument;
import com.ngleanhvu.common.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public class CandidateDocumentMapper {

  public CandidateDocument toDocument(Candidate candidate) {

    if (ValidationUtil.isNull(candidate)) {
      return null;
    }

    CandidateDocument document = new CandidateDocument();

    document.setId(candidate.getId().value());
    document.setUserId(candidate.getUserId());
    document.setEmail(candidate.getEmail().value());

    document.setProfile(candidate.getProfile());
    document.setAddress(candidate.getAddress());

    document.setStatus(candidate.getStatus().name());
    document.setSummary(candidate.getSummary());

    document.setSkills(candidate.getSkills());
    document.setExperiences(candidate.getExperiences());
    document.setEducations(candidate.getEducations());
    document.setSocialLinks(candidate.getSocialLinks());

    return document;
  }

  public Candidate toDomain(CandidateDocument document) {

    if (ValidationUtil.isNull(document)) {
      return null;
    }

    return Candidate.rehydrate(
        new CandidateId(document.getId()),
        document.getUserId(),
        new Email(document.getEmail()),
        document.getProfile(),
        document.getAddress(),
        CandidateStatus.from(document.getStatus()),
        document.getSummary(),
        document.getSkills(),
        document.getExperiences(),
        document.getEducations(),
        document.getSocialLinks());
  }
}
