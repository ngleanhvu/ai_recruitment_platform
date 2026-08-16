package com.ngleanhvu.job.application.port.input.job;

import com.ngleanhvu.job.domain.model.job.JobId;

public interface ExpireJobUseCase {
    void execute(JobId jobId);
}
