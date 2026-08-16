package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.EducationRequest;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;

import java.util.List;

public interface UpdateCandidateEducationUseCase {
    void execute(CandidateId candidateId, List<EducationRequest> request);
}
