package com.ngleanhvu.candidate.application.usecase.candidate;

import com.ngleanhvu.candidate.application.dto.request.CreateCandidateFromResumeRequest;
import com.ngleanhvu.candidate.application.mapper.CandidateMapper;
import com.ngleanhvu.candidate.domain.model.candidate.*;
import com.ngleanhvu.candidate.domain.repository.CandidateRepository;
import com.ngleanhvu.common.exception.ResourceAlreadyExistException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record CreateCandidateFromResumeUseCase(
    CandidateRepository candidateRepository, CandidateMapper candidateMapper) {
  public void execute(CreateCandidateFromResumeRequest request) {
    Email email = new Email(request.profile().email());
    boolean existedByEmail = candidateRepository.existByEmail(email);
    if (existedByEmail) {
      throw new ResourceAlreadyExistException("Candidate has already existed with this email");
    }
    Profile profile = candidateMapper.toProfile(request.profile());
    List<Education> educations =
        request.educations().stream().map(candidateMapper::toEducation).toList();
    List<Experience> experiences =
        request.experiences().stream().map(candidateMapper::toExperience).toList();
    List<Skill> skills = request.skills().stream().map(candidateMapper::toSkill).toList();
    List<SocialLink> socialLinks =
        request.socialLinks().stream().map(candidateMapper::toSocialLink).toList();
    Candidate candidate =
        Candidate.create(
            "", email.value(), profile.firstName(), profile.lastName(), profile.phone(), "");
    candidate.updateEducations(educations);
    candidate.updateExperiences(experiences);
    candidate.updateSkills(skills);
    candidate.updateSocialLinks(socialLinks);
    candidateRepository.save(candidate);
  }
}
