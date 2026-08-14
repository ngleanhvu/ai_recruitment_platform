package com.ngleanhvu.job.interfaces.rest;

import com.ngleanhvu.job.application.dto.request.CreateJobRequest;
import com.ngleanhvu.job.application.usecase.job.CreateJobUseCase;
import com.ngleanhvu.job.application.usecase.job.UpdateJobInformationUseCase;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.shared.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobs")
public record JobController (
        CreateJobUseCase createJobUseCase,
        UpdateJobInformationUseCase updateJobInformationUseCase
) {
    @PostMapping("/create")
    public ApiResponse<Void> createJob(@RequestBody CreateJobRequest request) {
        createJobUseCase.execute(request);
        return ApiResponse.success("Create job success", null);
    }

    @PutMapping("/{jobId}/update-information")
    public ApiResponse<Void> updateJobInformation(@RequestBody CreateJobRequest request,
                                                  @PathVariable("jobId") String jobId) {
        updateJobInformationUseCase.execute(request, new JobId(jobId));
        return ApiResponse.success("Update job success", null);
    }
}
