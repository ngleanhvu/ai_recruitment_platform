package com.ngleanhvu.domain.model.candidate;

import com.ngleanhvu.shared.exception.DomainException;
import com.ngleanhvu.shared.exception.ValidationException;
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
  private String summary;

  private final List<Skill> skills;
  private final List<Experience> experiences;
  private final List<Education> educations;
  private final List<SocialLink> socialLinks;

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
    if (ValidationUtil.isNull(profile))
      return;

    this.profile = profile;
  }

  public void updateEmail(Email email) {
    if (ValidationUtil.isNull(email))
      return;
    this.email = email;
  }

  public void addAddress(Address address) {
    if (ValidationUtil.isNull(address))
      return;
    this.address = address;
  }


  public void addSkills(List<Skill> newSkills) {

    if (ValidationUtil.isEmpty(newSkills)) {
      return;
    }
    newSkills.stream().filter(skill -> !skills.contains(skill)).forEach(skills::add);
  }

  public void addExperience(Experience experience) {
    if (ValidationUtil.isNull(experience)) throw new DomainException("Experience required");

    experiences.add(experience);
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
