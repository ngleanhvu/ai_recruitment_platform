package com.ngleanhvu.candidate.domain.model.candidate;

import com.ngleanhvu.shared.exception.ValidationException;
import com.ngleanhvu.shared.util.ValidationUtil;
import lombok.Builder;

@Builder
public record Profile(String firstName, String lastName, String phone, String avatarKey) {
  public Profile {
    if (ValidationUtil.isEmpty(phone)) throw new ValidationException("Phone should not be empty");
    if (!phone.matches("^(03|05|07|08|09)\\d{8}$"))
      throw new ValidationException("Phone is invalid");
  }
}
