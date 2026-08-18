package com.ngleanhvu.job.application.port.input.job;

import com.ngleanhvu.job.domain.model.job.JobId;
import java.util.List;

public interface UpdateJobBenefitUseCase {
  void execute(List<String> request, JobId jobId);
}
