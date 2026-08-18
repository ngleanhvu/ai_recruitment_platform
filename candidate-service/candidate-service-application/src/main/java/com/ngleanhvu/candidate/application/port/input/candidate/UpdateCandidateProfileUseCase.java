package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.ProfileRequest;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;

public interface UpdateCandidateProfileUseCase {
  void execute(CandidateId candidateId, ProfileRequest request);
}
