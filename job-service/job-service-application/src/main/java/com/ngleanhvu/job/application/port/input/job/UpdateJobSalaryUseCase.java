package com.ngleanhvu.job.application.port.input.job;

import com.ngleanhvu.job.application.dto.request.SalaryRangeRequest;
import com.ngleanhvu.job.domain.model.job.JobId;

public interface UpdateJobSalaryUseCase {
  void execute(SalaryRangeRequest request, JobId jobId);
}
