package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.input.candidate.GetCandidateDetailUseCase;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record GetCandidateDetailService(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) implements GetCandidateDetailUseCase {

  @Override
  public CandidateDetailResponse execute(CandidateId candidateId) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

    return candidateMapper.toDetail(candidate);
  }
}
