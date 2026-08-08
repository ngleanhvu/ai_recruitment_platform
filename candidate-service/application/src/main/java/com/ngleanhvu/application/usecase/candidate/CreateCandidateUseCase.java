package com.ngleanhvu.application.usecase.candidate;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public record CreateCandidateUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  public void execute(CreateCandidateRequest request) {
    Candidate candidate = candidateMapper.toDomain(request);
    candidateRepository.save(candidate);
  }
}
