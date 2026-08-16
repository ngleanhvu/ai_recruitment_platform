package com.ngleanhvu.job.application.port.input.job;

import com.ngleanhvu.job.application.dto.request.CreateJobRequest;

public interface CreateJobUseCase {
    void execute(CreateJobRequest request);
}
