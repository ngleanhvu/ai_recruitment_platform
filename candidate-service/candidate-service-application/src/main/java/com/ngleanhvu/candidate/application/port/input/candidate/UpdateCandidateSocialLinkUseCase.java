package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.SocialLinkRequest;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;

import java.util.List;

public interface UpdateCandidateSocialLinkUseCase {
    void execute(CandidateId candidateId, List<SocialLinkRequest> request);
}
