package com.ngleanhvu.job.interfaces.rest;

import com.ngleanhvu.job.application.dto.request.CreateJobRequest;
import com.ngleanhvu.job.application.usecase.job.CreateJobUseCase;
import com.ngleanhvu.shared.response.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jobs")
public record JobController (
        CreateJobUseCase createJobUseCase
) {
    @PostMapping("/create")
    public ApiResponse<Void> createJob(@RequestBody CreateJobRequest request) {
        createJobUseCase.execute(request);
        return ApiResponse.success("Create job success", null);
    }
}
