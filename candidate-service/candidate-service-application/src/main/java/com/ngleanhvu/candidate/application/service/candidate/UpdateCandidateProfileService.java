package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.dto.request.ProfileRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.input.candidate.UpdateCandidateProfileUseCase;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Email;
import com.ngleanhvu.candidate.domain.candidate.Profile;
import com.ngleanhvu.common.exception.ResourceAlreadyExistException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.util.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateProfileService(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper)
    implements UpdateCandidateProfileUseCase {
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
