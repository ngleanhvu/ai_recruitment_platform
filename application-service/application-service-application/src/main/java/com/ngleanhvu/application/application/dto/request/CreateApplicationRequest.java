package com.ngleanhvu.application.application.dto.request;

public record CreateApplicationRequest(
        String candidateId,
        String jobId,
        String resumeId,
        String coverLetter,
        String source
) {
}
