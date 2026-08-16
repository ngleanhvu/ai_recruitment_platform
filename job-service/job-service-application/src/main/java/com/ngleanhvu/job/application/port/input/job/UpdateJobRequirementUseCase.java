package com.ngleanhvu.job.application.port.input.job;

import com.ngleanhvu.job.application.dto.request.JobRequirementRequest;
import com.ngleanhvu.job.domain.model.job.JobId;

public interface UpdateJobRequirementUseCase {
    void execute(JobRequirementRequest request, JobId jobId);
}
