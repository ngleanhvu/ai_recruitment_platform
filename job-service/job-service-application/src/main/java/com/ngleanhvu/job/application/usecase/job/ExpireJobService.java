package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.application.port.input.job.ExpireJobUseCase;
import com.ngleanhvu.job.application.port.output.JobRepository;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import org.springframework.stereotype.Service;

@Service
public record ExpireJobService(JobRepository jobRepository, JobMapper jobMapper)
    implements ExpireJobUseCase {
  public void execute(JobId jobId) {
    Job job =
        jobRepository
            .findById(jobId)
            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
    job.expire();
    jobRepository.save(job);
  }
}
