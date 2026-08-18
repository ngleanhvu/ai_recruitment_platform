package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;

public interface GetCandidateDetailUseCase {
  CandidateDetailResponse execute(CandidateId candidateId);
}
