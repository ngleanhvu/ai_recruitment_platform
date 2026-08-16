package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.dto.request.EducationRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.input.candidate.UpdateCandidateEducationUseCase;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.Education;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateEducationService(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper)  implements UpdateCandidateEducationUseCase {
  public void execute(CandidateId candidateId, List<EducationRequest> request) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    List<Education> educations = request.stream().map(candidateMapper::toEducation).toList();
    candidate.updateEducations(educations);
    candidateRepository.save(candidate);
  }
}
