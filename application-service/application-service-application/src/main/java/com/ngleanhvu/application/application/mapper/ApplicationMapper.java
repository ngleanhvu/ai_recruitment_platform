package com.ngleanhvu.application.application.mapper;

import com.ngleanhvu.application.application.dto.request.CreateApplicationRequest;
import com.ngleanhvu.application.domain.model.application.Application;
import com.ngleanhvu.application.domain.model.application.enums.ApplicationStatus;
import com.ngleanhvu.application.domain.model.application.enums.Source;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {
    public Application toDomain(CreateApplicationRequest request) {
        return Application.create(
                request.candidateId(),
                request.resumeId(),
                request.jobId(),
                request.coverLetter(),
                Source.from(request.source()),
                ApplicationStatus.SCREENING
        );
    }
}
