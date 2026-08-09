package com.ngleanhvu.application.usecase.candidate;

import com.ngleanhvu.application.dto.request.UpdateExperiencesRequest;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.model.candidate.Experience;
import com.ngleanhvu.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateExperiencesUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  public void execute(CandidateId candidateId, List<UpdateExperiencesRequest> request) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    List<Experience> experiences = request.stream().map(candidateMapper::toExperience).toList();
    candidate.updateExperiences(experiences);
    candidateRepository.save(candidate);
  }
}
