package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.application.dto.request.AddressRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.domain.model.candidate.Address;
import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateAddressUseCase(
    CandidateMapper candidateMapper, CandidateRepository candidateRepository) {
  public void execute(CandidateId candidateId, AddressRequest request) {
    Address address = candidateMapper.toAddress(request);
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    candidate.updateAddress(address);
  }
}
