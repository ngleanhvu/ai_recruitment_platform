package com.ngleanhvu.infra.persistence.mapper;

import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.infra.persistence.documet.candidate.CandidateDocument;
import org.springframework.stereotype.Component;

@Component
public class CandidateDocumentMapper {
    public CandidateDocument toDocument(Candidate candidate) {
        CandidateDocument candidateDocument = new CandidateDocument();
        candidateDocument.setAddress(candidate.getAddress());
        candidateDocument.setSkills(candidate.getSkills());
        candidateDocument.setEducations(candidate.getEducations());
        candidateDocument.setProfile(candidate.getProfile());
        candidateDocument.setExperiences(candidate.getExperiences());
        candidateDocument.setSummary(candidate.getSummary());
        candidateDocument.setSocialLinks(candidate.getSocialLinks());
        candidateDocument.setUserId(candidate.getUserId());
        candidateDocument.setActive(candidate.isActive());
        candidateDocument.setDeleted(candidate.isDeleted());
        candidateDocument.setCreatedAt(candidate.getCreatedAt());
        candidateDocument.setCreatedAt(candidate.getCreatedAt());
        candidateDocument.setUpdatedAt(candidate.getUpdatedAt());
        candidateDocument.setCreatedBy(candidate.getCreatedBy());
        candidateDocument.setUpdatedBy(candidate.getUpdatedBy());
        return candidateDocument;
    }
}
