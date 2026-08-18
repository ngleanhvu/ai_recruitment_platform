package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.dto.request.AddressRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.input.candidate.UpdateCandidateAddressUseCase;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Address;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateAddressService(
    CandidateMapper candidateMapper, CandidateRepository candidateRepository)
    implements UpdateCandidateAddressUseCase {
  public void execute(CandidateId candidateId, AddressRequest request) {
    Address address = candidateMapper.toAddress(request);
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    candidate.updateAddress(address);
  }
}
