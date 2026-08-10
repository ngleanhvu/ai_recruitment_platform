package com.ngleanhvu.candidate.interfaces.rest;

import com.ngleanhvu.candidate.application.dto.request.*;
import com.ngleanhvu.candidate.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.candidate.application.usecase.candidate.*;
import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.shared.response.ApiResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/candidates")
@Slf4j
public record CandidateController(
    CreateCandidateUseCase createCandidateUseCase,
    GetCandidateDetailUseCase getCandidateDetailUseCase,
    UpdateCandidateSkillUseCase updateCandidateSkillUseCase,
    UpdateCandidateProfileUseCase updateProfileUseCase,
    UpdateCandidateAddressUseCase updateAddressUseCase,
    ActivateCandidateUseCase activateCandidateUseCase,
    BlockCandidateUseCase blockCandidateUseCase,
    UpdateCandidateEducationUseCase updateCandidateEducationUseCase,
    UpdateCandidateExperiencesUseCase updateCandidateExperiencesUseCase,
    UpdateCandidateSocialLinkUseCase updateCandidateSocialLinkUseCase,
    UpdateCandidateAvatarUseCase updateCandidateAvatarUseCase) {

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

  @PutMapping("/{candidateId}/update-skills")
  public ApiResponse<Void> updateSkills(
      @RequestBody List<UpdateSkillRequest> skills,
      @PathVariable("candidateId") String candidateId) {
    updateCandidateSkillUseCase.execute(new CandidateId(candidateId), skills);
    return ApiResponse.success("Update skill for candidate success", null);
  }

  @PutMapping("/{candidateId}/update-profile")
  public ApiResponse<Void> updateProfile(
          @RequestBody UpdateProfileRequest request, @PathVariable("candidateId") String candidateId) {
    updateProfileUseCase.execute(new CandidateId(candidateId), request);
    return ApiResponse.success("Update profile success", null);
  }

  @PutMapping("/{candidateId}/update-address")
  public ApiResponse<Void> updateAddress(
          @RequestBody AddressRequest request, @PathVariable("candidateId") String candidateId) {
    updateAddressUseCase.execute(new CandidateId(candidateId), request);
    return ApiResponse.success("Update address success", null);
  }

  @PutMapping("/{candidateId}/update-educations")
  public ApiResponse<Void> updateEducations(
      @RequestBody List<UpdateEducationRequest> request,
      @PathVariable("candidateId") String candidateId) {
    updateCandidateEducationUseCase.execute(new CandidateId(candidateId), request);
    return ApiResponse.success("Update education success", null);
  }

  @PutMapping("/{candidateId}/update-social-link")
  public ApiResponse<Void> updateSocialLinks(
      @RequestBody List<UpdateSocialLinkRequest> request,
      @PathVariable("candidateId") String candidateId) {
    updateCandidateSocialLinkUseCase.execute(new CandidateId(candidateId), request);
    return ApiResponse.success("Update social link success", null);
  }

  @PutMapping("/{candidateId}/update-experiences")
  public ApiResponse<Void> updateExperiences(
      @RequestBody List<UpdateExperiencesRequest> request,
      @PathVariable("candidateId") String candidateId) {
    updateCandidateExperiencesUseCase.execute(new CandidateId(candidateId), request);
    return ApiResponse.success("Update experience success", null);
  }

  @PatchMapping("/{candidateId}/block")
  public ApiResponse<Void> block(@PathVariable("candidateId") String candidateId) {
    blockCandidateUseCase.execute(new CandidateId(candidateId));
    return ApiResponse.success("Block success", null);
  }

  @PatchMapping("/{candidateId}/activate")
  public ApiResponse<Void> activate(@PathVariable("candidateId") String candidateId) {
    activateCandidateUseCase.execute(new CandidateId(candidateId));
    return ApiResponse.success("Activate success", null);
  }

  @PostMapping("/{candidateId}/upload-avatar")
  public ApiResponse<Void> uploadAvatar(
      @PathVariable("candidateId") String candidateId,
      @RequestPart("avatar") MultipartFile avatar) {
    updateCandidateAvatarUseCase.execute(new CandidateId(candidateId), avatar);

    return ApiResponse.success("Upload avatar success", null);
  }
}
