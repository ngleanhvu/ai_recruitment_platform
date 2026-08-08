package com.ngleanhvu.application.usecase.candidate;

import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.repository.CandidateRepository;
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
