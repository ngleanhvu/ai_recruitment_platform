package com.ngleanhvu.application.usecase.candidate;

import com.ngleanhvu.application.dto.request.AddressRequest;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.domain.model.candidate.Address;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record AddAddressUseCase(
    CandidateMapper candidateMapper, CandidateRepository candidateRepository) {
  public void execute(CandidateId candidateId, AddressRequest request) {
    Address address = candidateMapper.toAddress(request);
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    candidate.addAddress(address);
  }
}
