package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.application.dto.request.UpdateSkillRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.model.candidate.Skill;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateSkillUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {

  public void execute(CandidateId candidateId, List<UpdateSkillRequest> request) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

    List<Skill> skills = request.stream().map(candidateMapper::toSkill).toList();

    candidate.updateSkills(skills);

    candidateRepository.save(candidate);
  }
}
