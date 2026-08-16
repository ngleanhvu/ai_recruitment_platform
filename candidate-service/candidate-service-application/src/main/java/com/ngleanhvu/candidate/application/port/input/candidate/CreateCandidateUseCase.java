package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.CreateCandidateRequest;

public interface CreateCandidateUseCase {
    void execute(CreateCandidateRequest request);
}
