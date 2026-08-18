package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.domain.candidate.CandidateId;

public interface ExistCandidateByIdUseCase {
  boolean execute(CandidateId candidateId);
}
