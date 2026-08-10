package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.application.dto.request.UpdateSocialLinkRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.domain.model.candidate.Candidate;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.model.candidate.SocialLink;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateSocialLinkUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  public void execute(CandidateId candidateId, List<UpdateSocialLinkRequest> request) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    List<SocialLink> socialLinks = request.stream().map(candidateMapper::toSocialLink).toList();
    candidate.updateSocialLinks(socialLinks);
    candidateRepository.save(candidate);
  }
}
