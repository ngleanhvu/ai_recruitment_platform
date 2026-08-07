package com.ngleanhvu.infra.persistence.mapper;

import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.model.candidate.CandidateStatus;
import com.ngleanhvu.domain.model.candidate.Email;
import com.ngleanhvu.infra.persistence.documet.candidate.CandidateDocument;
import org.springframework.stereotype.Component;

@Component
public class CandidateDocumentMapper {

    public CandidateDocument toDocument(Candidate candidate) {

        if (candidate == null) {
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

        if (document == null) {
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
                document.getSocialLinks()
        );
    }
}
