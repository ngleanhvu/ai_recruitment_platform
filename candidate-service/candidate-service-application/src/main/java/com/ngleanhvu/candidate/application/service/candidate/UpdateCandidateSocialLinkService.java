package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.dto.request.SocialLinkRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.application.port.input.candidate.UpdateCandidateSocialLinkUseCase;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.candidate.SocialLink;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record UpdateCandidateSocialLinkService(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) implements UpdateCandidateSocialLinkUseCase {
  public void execute(CandidateId candidateId, List<SocialLinkRequest> request) {
    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));
    List<SocialLink> socialLinks = request.stream().map(candidateMapper::toSocialLink).toList();
    candidate.updateSocialLinks(socialLinks);
    candidateRepository.save(candidate);
  }
}
