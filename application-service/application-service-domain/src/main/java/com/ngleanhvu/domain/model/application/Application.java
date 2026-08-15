package com.ngleanhvu.domain.model.application;

import com.ngleanhvu.domain.model.application.enums.ApplicationStatus;
import com.ngleanhvu.domain.model.application.enums.Source;
import lombok.Getter;

@Getter
public final class Application {
    private final ApplicationId id;
    private final String candidateId;
    private final String resumeId;
    private final String jobId;
    private final String coverLetter;
    private final Source source;
    private final ApplicationStatus status;
    private final MatchingResult matching;

    public Application(ApplicationId id, String candidateId, String resumeId,
                       String jobId, String coverLetter, Source source,
                       ApplicationStatus status, MatchingResult matching) {
        this.id = id;
        this.candidateId = candidateId;
        this.resumeId = resumeId;
        this.jobId = jobId;
        this.coverLetter = coverLetter;
        this.source = source;
        this.status = status;
        this.matching = matching;
    }

    public static Application create(
            String candidateId,
            String resumeId,
            String jobId,
            String coverLetter,
            Source source,
            ApplicationStatus status
    ) {
        return new Application(
                ApplicationId.generate(),
                candidateId,
                resumeId,
                jobId,
                coverLetter,
                source,
                status,
                null
        );
    }
}
