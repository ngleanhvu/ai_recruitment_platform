package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record BlockCandidateUseCase(CandidateRepository candidateRepository) {
  public void execute(CandidateId candidateId) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    candidate.block();
    candidateRepository.save(candidate);
  }
}
