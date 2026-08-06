package com.ngleanhvu.interfaces.rest;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.service.candidate.CandidateAppService;
import com.ngleanhvu.shared.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/candidates")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateAppService candidateAppService;

    @PostMapping
    public ApiResponse<Void> createCandidate(@RequestBody CreateCandidateRequest request) {
        candidateAppService.createCandidate(request);
        return ApiResponse.success("Create candidate success", null);
    }
}
