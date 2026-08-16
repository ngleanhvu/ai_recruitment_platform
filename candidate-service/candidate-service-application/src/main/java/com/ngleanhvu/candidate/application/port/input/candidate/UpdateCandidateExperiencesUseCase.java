package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.ExperiencesRequest;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;

import java.util.List;

public interface UpdateCandidateExperiencesUseCase {
    void execute(CandidateId candidateId, List<ExperiencesRequest> request);
}
