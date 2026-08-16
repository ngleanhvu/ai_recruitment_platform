package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.input.candidate.CreateCandidateUseCase;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceAlreadyExistException;
import org.springframework.stereotype.Service;

@Service
public record CreateCandidateService(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) implements CreateCandidateUseCase {
  public void execute(CreateCandidateRequest request) {
    Email email = new Email(request.email());
    if (candidateRepository.existByEmail(email))
      throw new ResourceAlreadyExistException(
          String.format("Candidate with email %s already existed", request.email()));
    Candidate candidate = candidateMapper.toDomain(request);
    candidateRepository.save(candidate);
  }
}
