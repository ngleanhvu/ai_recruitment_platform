package com.ngleanhvu.candidate.domain.model.candidate;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;
import lombok.Builder;

@Builder
public record SocialLink(String type, String url) {
  public SocialLink {
    if (ValidationUtil.isEmpty(type)) throw new ValidationException("Type must not be empty");
    if (ValidationUtil.isEmpty(url)) throw new ValidationException("Url must not be empty");
  }
}
