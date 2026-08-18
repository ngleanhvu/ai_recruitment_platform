package com.ngleanhvu.job.application.port.input.job;

import com.ngleanhvu.job.domain.model.job.JobId;

public interface PublishJobUseCase {
  void execute(JobId jobId);
}
