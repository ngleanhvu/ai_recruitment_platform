package com.ngleanhvu.interfaces.rest;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.request.SkillRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.application.usecase.candidate.AddSkillForCandidateUseCase;
import com.ngleanhvu.application.usecase.candidate.CreateCandidateUseCase;
import com.ngleanhvu.application.usecase.candidate.GetCandidateDetailUseCase;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.shared.response.ApiResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/candidates")
@Slf4j
public record CandidateController(
    CreateCandidateUseCase createCandidateUseCase,
    GetCandidateDetailUseCase getCandidateDetailUseCase,
    AddSkillForCandidateUseCase addSkillForCandidateUseCase) {

  @PostMapping
  public ApiResponse<Void> createCandidate(@RequestBody CreateCandidateRequest request) {
    createCandidateUseCase.execute(request);
    return ApiResponse.success("Create candidate success", null);
  }

  @GetMapping("/{candidateId}")
  public ApiResponse<CandidateDetailResponse> getCandidateDetail(
      @PathVariable("candidateId") String candidateId) {
    CandidateDetailResponse candidateDetailResponse =
        getCandidateDetailUseCase.execute(new CandidateId(candidateId));
    return ApiResponse.success("Get candidate detail success", candidateDetailResponse);
  }

  @PutMapping("/{candidateId}/skills")
  public ApiResponse<Void> addSkills(
      @RequestBody List<SkillRequest> skills, @PathVariable("candidateId") String candidateId) {
    addSkillForCandidateUseCase.execute(new CandidateId(candidateId), skills);
    return ApiResponse.success("Add skill for candidate success", null);
  }
}
