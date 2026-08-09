package com.ngleanhvu.application.usecase.candidate;

import com.ngleanhvu.application.dto.request.UpdateSkillRequest;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.model.candidate.Skill;
import com.ngleanhvu.domain.repository.CandidateRepository;
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
