package com.ngleanhvu.job.application.port.input.job;

import com.ngleanhvu.job.application.dto.request.CreateJobRequest;
import com.ngleanhvu.job.domain.model.job.JobId;

public interface UpdateJobInformationUseCase {
    void execute(CreateJobRequest request, JobId jobId);
}
