package com.ngleanhvu.application.infra.persistence.mapper;

import com.ngleanhvu.application.infra.persistence.document.application.ApplicationDocument;
import com.ngleanhvu.common.util.ValidationUtil;
import com.ngleanhvu.domain.model.application.Application;
import com.ngleanhvu.domain.model.application.ApplicationId;
import com.ngleanhvu.domain.model.application.enums.ApplicationStatus;
import com.ngleanhvu.domain.model.application.enums.Source;
import org.springframework.stereotype.Component;

@Component
public class ApplicationDocumentMapper {
    public Application toDomain(ApplicationDocument document) {
        if (ValidationUtil.isNull(document)) {
            return null;
        }
        return new Application(
                new ApplicationId(document.getId()),
                document.getCandidateId(),
                document.getResumeId(),
                document.getResumeId(),
                document.getCoverLetter(),
                Source.from(document.getSource()),
                ApplicationStatus.from(document.getStatus()),
                document.getMatching(),
                document.getUploadAt()
        );
    }

    public ApplicationDocument toDocument(Application domain) {
        if (ValidationUtil.isNull(domain)) {
            return null;
        }

        ApplicationDocument document = new ApplicationDocument();
        document.setId(domain.getId().value());
        document.setCandidateId(domain.getCandidateId());
        document.setResumeId(domain.getResumeId());
        document.setJobId(domain.getJobId());
        document.setCoverLetter(domain.getCoverLetter());
        document.setSource(domain.getSource().name());
        document.setStatus(domain.getStatus().name());
        document.setMatching(domain.getMatching());
        document.setUploadAt(domain.getUploadAt());
        return document;
    }
}
