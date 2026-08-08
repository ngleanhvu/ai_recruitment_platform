package com.ngleanhvu.application.usecase.candidate;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.Email;
import com.ngleanhvu.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public record CreateCandidateUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  public void execute(CreateCandidateRequest request) {
    Email email = new Email(request.email());
    if (candidateRepository.existByEmail(email))
      throw new ResourceAlreadyExistException(String.format("Candidate with email %s already existed", request.email()));
    Candidate candidate = candidateMapper.toDomain(request);
    candidateRepository.save(candidate);
  }
}
