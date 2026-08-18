package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.port.input.candidate.BlockCandidateUseCase;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record BlockCandidateService(CandidateRepository candidateRepository)
    implements BlockCandidateUseCase {
  public void execute(CandidateId candidateId) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    candidate.block();
    candidateRepository.save(candidate);
  }
}
