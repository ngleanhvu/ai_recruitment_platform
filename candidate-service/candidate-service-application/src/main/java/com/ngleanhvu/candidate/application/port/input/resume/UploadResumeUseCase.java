package com.ngleanhvu.candidate.application.port.input.resume;

import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import org.springframework.web.multipart.MultipartFile;

public interface UploadResumeUseCase {
  void execute(CandidateId candidateId, MultipartFile file);
}
