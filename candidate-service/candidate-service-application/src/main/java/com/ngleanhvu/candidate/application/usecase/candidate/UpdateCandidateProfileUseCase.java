package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.application.dto.request.ProfileRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.model.candidate.Email;
import com.ngleanhvu.candidate.domain.model.candidate.Profile;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceAlreadyExistException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.util.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateProfileUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  public void execute(CandidateId candidateId, ProfileRequest request) {
    Profile profile = candidateMapper.toProfile(request);
    Email email = new Email(request.email());

    Candidate candidate = candidateRepository.findByEmail(email).orElse(null);

    if (!ValidationUtil.isNull(candidate) && !candidate.getId().value().equals(candidateId.value()))
      throw new ResourceAlreadyExistException(
          String.format("Another candidate with email %s already existed", email.value()));

    if (!ValidationUtil.isNull(candidate)
        && candidateRepository.existByPhone(request.phone())
        && !candidate.getProfile().phone().equals(request.phone()))
      throw new ResourceAlreadyExistException(
          String.format("Another candidate with phone %s already existed", request.phone()));

    if (ValidationUtil.isNull(candidate))
      candidate =
          candidateRepository
              .findById(candidateId)
              .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

    candidate.updateProfile(profile);
    candidate.updateEmail(email);

    candidateRepository.save(candidate);
  }
}
