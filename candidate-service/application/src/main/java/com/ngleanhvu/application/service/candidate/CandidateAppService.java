package com.ngleanhvu.application.service.candidate;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;

public interface CandidateAppService {
    void createCandidate(CreateCandidateRequest request);
    CandidateDetailResponse getCandidateDetail(String candidateId);
}
