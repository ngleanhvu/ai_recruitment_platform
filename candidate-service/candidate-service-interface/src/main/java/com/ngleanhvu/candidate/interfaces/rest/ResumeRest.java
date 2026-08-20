package com.ngleanhvu.candidate.interfaces.rest;

import com.ngleanhvu.candidate.application.port.input.resume.UploadResumeUseCase;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.common.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/candidates")
public record ResumeRest(UploadResumeUseCase uploadResumeUseCase) {
  @PostMapping("/{candidateId}/resume/upload")
  public ApiResponse<Void> uploadResume(
      @RequestPart("file") MultipartFile file, @PathVariable("candidateId") String candidateId) {
    uploadResumeUseCase.execute(new CandidateId(candidateId), file);
    return ApiResponse.success("Upload resume success", null);
  }
}
