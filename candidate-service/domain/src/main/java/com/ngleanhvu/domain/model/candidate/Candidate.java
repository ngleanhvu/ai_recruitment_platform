package com.ngleanhvu.domain.model.candidate;

import com.ngleanhvu.shared.exception.DomainException;
import com.ngleanhvu.shared.util.ValidationUtil;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public final class Candidate {

  private final CandidateId id;
  private final String userId;
  private Email email;

  private Profile profile;
  private Address address;
  private CandidateStatus status;
  private final String summary;

  private List<Skill> skills;
  private List<Experience> experiences;
  private List<Education> educations;
  private List<SocialLink> socialLinks;

  private Candidate(
      CandidateId id,
      String userId,
      Email email,
      Profile profile,
      Address address,
      CandidateStatus status,
      String summary,
      List<Skill> skills,
      List<Experience> experiences,
      List<Education> educations,
      List<SocialLink> socialLinks) {
    this.id = id;
    this.userId = userId;
    this.email = email;
    this.profile = profile;
    this.address = address;
    this.status = status;
    this.summary = summary;

    this.skills = skills != null ? new ArrayList<>(skills) : new ArrayList<>();

    this.experiences = experiences != null ? new ArrayList<>(experiences) : new ArrayList<>();

    this.educations = educations != null ? new ArrayList<>(educations) : new ArrayList<>();

    this.socialLinks = socialLinks != null ? new ArrayList<>(socialLinks) : new ArrayList<>();
  }

  public static Candidate create(
      String userId,
      String email,
      String firstName,
      String lastName,
      String phone,
      String summary) {

    return new Candidate(
        CandidateId.generate(),
        userId,
        new Email(email),
        new Profile(firstName, lastName, phone, ""),
        null,
        CandidateStatus.PENDING,
        summary,
        null,
        null,
        null,
        null);
  }

  public static Candidate rehydrate(
      CandidateId id,
      String userId,
      Email email,
      Profile profile,
      Address address,
      CandidateStatus status,
      String summary,
      List<Skill> skills,
      List<Experience> experiences,
      List<Education> educations,
      List<SocialLink> socialLinks) {
    return new Candidate(
        id,
        userId,
        email,
        profile,
        address,
        status,
        summary,
        skills,
        experiences,
        educations,
        socialLinks);
  }

  public void updateProfile(Profile profile) {
    if (ValidationUtil.isNull(profile)) return;

    this.profile = profile;
  }

  public void updateEmail(Email email) {
    if (ValidationUtil.isNull(email)) return;
    this.email = email;
  }

  public void updateAddress(Address address) {
    if (ValidationUtil.isNull(address)) return;
    this.address = address;
  }

  public void updateSkills(List<Skill> newSkills) {

    if (ValidationUtil.isEmpty(newSkills)) {
      return;
    }

    this.skills = newSkills;
  }

  public void updateExperiences(List<Experience> newExperiences) {
    if (ValidationUtil.isNull(newExperiences)) return;

    this.experiences = newExperiences;
  }

  public void updateEducations(List<Education> newEducations) {
    if (ValidationUtil.isNull(newEducations)) return;
    this.educations = newEducations;
  }

  public void updateSocialLinks(List<SocialLink> newSocialLinks) {
    if (ValidationUtil.isNull(newSocialLinks)) return;
    this.socialLinks = newSocialLinks;
  }

  public void activate() {
    if (status == CandidateStatus.BLOCKED)
      throw new DomainException("Blocked candidate cannot activate");

    this.status = CandidateStatus.ACTIVE;
  }

  public void block() {
    if (status == CandidateStatus.BLOCKED) throw new DomainException("Candidate already blocked");
    this.status = CandidateStatus.BLOCKED;
  }
}
