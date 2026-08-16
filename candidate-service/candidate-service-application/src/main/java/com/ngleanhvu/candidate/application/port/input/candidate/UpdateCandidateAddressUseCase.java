package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.application.dto.request.AddressRequest;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;

public interface UpdateCandidateAddressUseCase {
    void execute(CandidateId candidateId, AddressRequest request);
}
