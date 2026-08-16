package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.SkillRequest;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;

import java.util.List;

public interface UpdateCandidateSkillUseCase {
    void execute(CandidateId candidateId, List<SkillRequest> request);
}
