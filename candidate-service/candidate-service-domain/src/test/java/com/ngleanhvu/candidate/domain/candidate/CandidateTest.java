package com.ngleanhvu.candidate.domain.candidate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ngleanhvu.candidate.domain.candidate.enums.CandidateStatus;
import com.ngleanhvu.common.exception.BusinessException;
import com.ngleanhvu.common.exception.ValidationException;
import java.util.List;
import org.junit.jupiter.api.Test;

class CandidateTest {

  @Test
  void createInitializesPendingCandidateWithEmptyCollections() {
    Candidate candidate =
        Candidate.create(
            "user-1", "candidate@example.com", "An", "Nguyen", "0912345678", "Summary");

    assertEquals("user-1", candidate.getUserId());
    assertEquals("candidate@example.com", candidate.getEmail().value());
    assertEquals(CandidateStatus.PENDING, candidate.getStatus());
    assertEquals("An", candidate.getProfile().firstName());
    assertEquals("", candidate.getProfile().avatarKey());
    assertEquals(List.of(), candidate.getSkills());
    assertEquals(List.of(), candidate.getExperiences());
    assertEquals(List.of(), candidate.getEducations());
    assertEquals(List.of(), candidate.getSocialLinks());
  }

  @Test
  void updateMethodsReplaceCandidateDetails() {
    Candidate candidate = candidate(CandidateStatus.PENDING);
    Profile profile = new Profile("Binh", "Tran", "0987654321", "avatar.png");
    Email email = new Email("updated@example.com");
    Address address = new Address("1 Main St", "District 1", "Vietnam", "HCM");
    Skill skill = new Skill("Java", "Senior", 5);
    Education education = new Education("University", "CS", "BS", 3.5, 2018, 2022);
    SocialLink socialLink = new SocialLink("github", "https://github.com/candidate");

    candidate.updateProfile(profile);
    candidate.updateEmail(email);
    candidate.updateAddress(address);
    candidate.updateSkills(List.of(skill));
    candidate.updateEducations(List.of(education));
    candidate.updateSocialLinks(List.of(socialLink));

    assertEquals(profile, candidate.getProfile());
    assertEquals(email, candidate.getEmail());
    assertEquals(address, candidate.getAddress());
    assertEquals(List.of(skill), candidate.getSkills());
    assertEquals(List.of(education), candidate.getEducations());
    assertEquals(List.of(socialLink), candidate.getSocialLinks());
  }

  @Test
  void nullOrEmptyUpdatesLeaveExistingValuesUnchanged() {
    Candidate candidate = candidate(CandidateStatus.PENDING);
    Profile profile = candidate.getProfile();
    Email email = candidate.getEmail();

    candidate.updateProfile(null);
    candidate.updateEmail(null);
    candidate.updateAddress(null);
    candidate.updateSkills(null);
    candidate.updateSkills(List.of());
    candidate.updateExperiences(null);
    candidate.updateEducations(null);
    candidate.updateSocialLinks(null);

    assertEquals(profile, candidate.getProfile());
    assertEquals(email, candidate.getEmail());
    assertEquals(List.of(), candidate.getSkills());
  }

  @Test
  void activateChangesPendingCandidateToActive() {
    Candidate candidate = candidate(CandidateStatus.PENDING);

    candidate.activate();

    assertEquals(CandidateStatus.ACTIVE, candidate.getStatus());
  }

  @Test
  void blockedCandidateCannotBeActivated() {
    Candidate candidate = candidate(CandidateStatus.BLOCKED);

    BusinessException exception = assertThrows(BusinessException.class, candidate::activate);

    assertEquals("Blocked candidate cannot activate", exception.getMessage());
    assertEquals(CandidateStatus.BLOCKED, candidate.getStatus());
  }

  @Test
  void blockRejectsAlreadyBlockedCandidate() {
    Candidate candidate = candidate(CandidateStatus.PENDING);
    candidate.block();

    BusinessException exception = assertThrows(BusinessException.class, candidate::block);

    assertEquals("Candidate already blocked", exception.getMessage());
    assertEquals(CandidateStatus.BLOCKED, candidate.getStatus());
  }

  @Test
  void updateAvatarPreservesProfileAndRejectsEmptyKey() {
    Candidate candidate = candidate(CandidateStatus.PENDING);

    candidate.updateAvatar("candidate/avatar.png");

    assertEquals("candidate/avatar.png", candidate.getProfile().avatarKey());
    assertEquals("An", candidate.getProfile().firstName());
    assertThrows(ValidationException.class, () -> candidate.updateAvatar(""));
  }

  private static Candidate candidate(CandidateStatus status) {
    return Candidate.rehydrate(
        new CandidateId("candidate-1"),
        "user-1",
        new Email("candidate@example.com"),
        new Profile("An", "Nguyen", "0912345678", ""),
        null,
        status,
        "Summary",
        null,
        null,
        null,
        null);
  }
}
