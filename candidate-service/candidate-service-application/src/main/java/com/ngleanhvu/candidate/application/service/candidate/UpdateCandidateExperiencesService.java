package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.dto.request.ExperiencesRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.input.candidate.UpdateCandidateExperiencesUseCase;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Experience;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateExperiencesService(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper)
    implements UpdateCandidateExperiencesUseCase {
  public void execute(CandidateId candidateId, List<ExperiencesRequest> request) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    List<Experience> experiences = request.stream().map(candidateMapper::toExperience).toList();
    candidate.updateExperiences(experiences);
    candidateRepository.save(candidate);
  }
}
