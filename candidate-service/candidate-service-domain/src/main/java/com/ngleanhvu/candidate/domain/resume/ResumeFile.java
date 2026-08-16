package com.ngleanhvu.candidate.domain.resume;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;

public record ResumeFile(String fileName, String fileKey) {
  public ResumeFile {
    if (ValidationUtil.isEmpty(fileKey))
      throw new ValidationException("File key must not be empty");
    if (ValidationUtil.isEmpty(fileName))
      throw new ValidationException("File name must not be empty");
  }
}
