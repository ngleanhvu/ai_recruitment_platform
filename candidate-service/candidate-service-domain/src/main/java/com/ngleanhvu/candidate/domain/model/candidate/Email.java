package com.ngleanhvu.candidate.domain.model.candidate;

import com.ngleanhvu.shared.exception.ValidationException;
import lombok.Builder;

@Builder
public record Email(String value) {
  public Email {
    if (value == null || value.isBlank()) {
      throw new ValidationException("Email is required");
    }

    if (!value.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
      throw new ValidationException("Invalid email format");
    }
  }
}
