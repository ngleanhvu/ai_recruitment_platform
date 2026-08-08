package com.ngleanhvu.interfaces.rest;

import com.ngleanhvu.application.dto.request.AddressRequest;
import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.request.SkillRequest;
import com.ngleanhvu.application.dto.request.UpdateProfileRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.application.usecase.candidate.*;
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
    AddSkillForCandidateUseCase addSkillForCandidateUseCase,
    UpdateProfileUseCase updateProfileUseCase,
    AddAddressUseCase addAddressUseCase) {

  @PostMapping("/create")
  public ApiResponse<Void> createCandidate(@RequestBody CreateCandidateRequest request) {
    createCandidateUseCase.execute(request);
    return ApiResponse.success("Create candidate success", null);
  }

  @GetMapping("/{candidateId}/get-detail")
  public ApiResponse<CandidateDetailResponse> getCandidateDetail(
      @PathVariable("candidateId") String candidateId) {
    CandidateDetailResponse candidateDetailResponse =
        getCandidateDetailUseCase.execute(new CandidateId(candidateId));
    return ApiResponse.success("Get candidate detail success", candidateDetailResponse);
  }

  @PutMapping("/{candidateId}/add-skills")
  public ApiResponse<Void> addSkills(
      @RequestBody List<SkillRequest> skills, @PathVariable("candidateId") String candidateId) {
    addSkillForCandidateUseCase.execute(new CandidateId(candidateId), skills);
    return ApiResponse.success("Add skill for candidate success", null);
  }

  @PutMapping("/{candidateId}/update-profile")
  public ApiResponse<Void> updateProfile(
      @RequestBody UpdateProfileRequest request,
      @PathVariable("candidateId") CandidateId candidateId) {
    updateProfileUseCase.execute(candidateId, request);
    return ApiResponse.success("Update profile success", null);
  }

  @PutMapping("/{candidateId}/add-address")
  public ApiResponse<Void> addAddress(
      @RequestBody AddressRequest request, @PathVariable("candidateId") CandidateId candidateId) {
    addAddressUseCase.execute(candidateId, request);
    return ApiResponse.success("Add address success", null);
  }
}
