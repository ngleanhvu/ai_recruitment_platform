package com.ngleanhvu.candidate.domain.candidate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ngleanhvu.common.exception.ValidationException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class CandidateValueObjectsTest {

  @Test
  void identifiersGenerateNonEmptyValuesAndRejectEmptyValues() {
    assertFalse(CandidateId.generate().value().isBlank());
    assertThrows(ValidationException.class, () -> new CandidateId(""));
    assertThrows(ValidationException.class, () -> new CandidateId(null));
  }

  @Test
  void emailRequiresAValidAddress() {
    assertDoesNotThrow(() -> new Email("candidate+jobs@example.com"));
    assertThrows(ValidationException.class, () -> new Email(null));
    assertThrows(ValidationException.class, () -> new Email(" "));
    assertThrows(ValidationException.class, () -> new Email("invalid"));
  }

  @Test
  void profileRequiresVietnameseMobileNumber() {
    assertDoesNotThrow(() -> new Profile("An", "Nguyen", "0912345678", ""));
    assertThrows(ValidationException.class, () -> new Profile("An", "Nguyen", "", ""));
    assertThrows(ValidationException.class, () -> new Profile("An", "Nguyen", "0112345678", ""));
  }

  @Test
  void skillRequiresNameLevelAndPositiveExperience() {
    assertDoesNotThrow(() -> new Skill("Java", "Senior", 5));
    assertThrows(ValidationException.class, () -> new Skill("", "Senior", 5));
    assertThrows(ValidationException.class, () -> new Skill("Java", "", 5));
    assertThrows(ValidationException.class, () -> new Skill("Java", "Senior", 0));
  }

  @Test
  void educationEnforcesSchoolGpaAndYearRange() {
    assertDoesNotThrow(() -> new Education("University", "CS", "BS", 4.0, 2018, 2022));
    assertThrows(
        ValidationException.class, () -> new Education("University", "CS", "BS", -0.1, 2018, 2022));
    assertThrows(
        ValidationException.class, () -> new Education("University", "CS", "BS", 4.1, 2018, 2022));
    assertThrows(ValidationException.class, () -> new Education("", "CS", "BS", 3.0, 2018, 2022));
    assertThrows(
        ValidationException.class, () -> new Education("University", "CS", "BS", 3.0, 2023, 2022));
  }

  @Test
  void experienceRequiresCompanyAndChronologicalDates() {
    LocalDate start = LocalDate.of(2020, 1, 1);
    LocalDate end = LocalDate.of(2022, 1, 1);

    assertDoesNotThrow(() -> new Experience("Company", "Engineer", "Work", start, end, false));
    assertThrows(
        ValidationException.class, () -> new Experience("", "Engineer", "Work", start, end, false));
    assertThrows(
        ValidationException.class,
        () -> new Experience("Company", "Engineer", "Work", end, start, false));
    assertThrows(
        ValidationException.class,
        () -> new Experience("Company", "Engineer", "Work", null, end, false));
    assertDoesNotThrow(
        () -> new Experience("Company", "Engineer", "Current role", start, null, true));
  }

  @Test
  void socialLinkRequiresTypeAndUrl() {
    assertDoesNotThrow(() -> new SocialLink("github", "https://github.com/candidate"));
    assertThrows(
        ValidationException.class, () -> new SocialLink("", "https://github.com/candidate"));
    assertThrows(ValidationException.class, () -> new SocialLink("github", ""));
  }
}
