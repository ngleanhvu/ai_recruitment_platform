package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.CreateCandidateFromResumeRequest;

public interface CreateCandidateFromResumeUseCase {
  void execute(CreateCandidateFromResumeRequest request);
}
