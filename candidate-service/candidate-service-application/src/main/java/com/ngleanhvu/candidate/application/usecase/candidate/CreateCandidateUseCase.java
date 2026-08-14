package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.Email;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public record CreateCandidateUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  public void execute(CreateCandidateRequest request) {
    Email email = new Email(request.email());
    if (candidateRepository.existByEmail(email))
      throw new ResourceAlreadyExistException(
          String.format("Candidate with email %s already existed", request.email()));
    Candidate candidate = candidateMapper.toDomain(request);
    candidateRepository.save(candidate);
  }
}
