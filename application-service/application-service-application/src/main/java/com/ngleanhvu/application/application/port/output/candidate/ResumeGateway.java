package com.ngleanhvu.application.application.port.output.candidate;

public interface ResumeGateway {
    boolean existsResumeByIdAndCandidateId(String resumeId, String candidateId);
}
