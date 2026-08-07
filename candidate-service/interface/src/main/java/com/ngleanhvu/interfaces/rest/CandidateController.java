package com.ngleanhvu.interfaces.rest;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.application.service.candidate.CandidateAppService;
import com.ngleanhvu.shared.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/candidates")
@RequiredArgsConstructor
@Slf4j
public class CandidateController {
    private final CandidateAppService candidateAppService;

    @PostMapping
    public ApiResponse<Void> createCandidate(
            @RequestBody CreateCandidateRequest request) {
        candidateAppService.createCandidate(request);
        return ApiResponse.success("Create candidate success", null);
    }

    @GetMapping("/{candidateId}")
    public ApiResponse<CandidateDetailResponse> getCandidateDetail(
            @PathVariable("candidateId") String candidateId) {
        CandidateDetailResponse candidateDetailResponse = candidateAppService.getCandidateDetail(candidateId);
        return ApiResponse.success("Get candidate detail success", candidateDetailResponse);
    }
}
