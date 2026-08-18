package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.application.port.input.job.CloseJobUseCase;
import com.ngleanhvu.job.application.port.output.JobRepository;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import org.springframework.stereotype.Service;

@Service
public record CloseJobService(JobRepository jobRepository, JobMapper jobMapper)
    implements CloseJobUseCase {
  public void execute(JobId jobId) {
    Job job =
        jobRepository
            .findById(jobId)
            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
    job.close();
    jobRepository.save(job);
  }
}
