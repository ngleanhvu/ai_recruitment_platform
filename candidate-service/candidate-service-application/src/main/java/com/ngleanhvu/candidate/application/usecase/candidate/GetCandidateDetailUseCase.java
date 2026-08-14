package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public record GetCandidateDetailUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  @Transactional(readOnly = true)
  public CandidateDetailResponse execute(CandidateId candidateId) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

    return candidateMapper.toDetail(candidate);
  }
}
