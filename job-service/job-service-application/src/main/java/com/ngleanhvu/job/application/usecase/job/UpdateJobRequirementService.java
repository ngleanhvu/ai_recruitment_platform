package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.job.application.dto.request.JobRequirementRequest;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.application.port.input.job.UpdateJobRequirementUseCase;
import com.ngleanhvu.job.application.port.output.JobRepository;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.job.domain.model.job.JobRequirements;
import org.springframework.stereotype.Service;

@Service
public record UpdateJobRequirementService(JobRepository jobRepository, JobMapper jobMapper)
    implements UpdateJobRequirementUseCase {
  public void execute(JobRequirementRequest request, JobId jobId) {
    Job job =
        jobRepository
            .findById(jobId)
            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

    JobRequirements jobRequirements = jobMapper.toJobRequirements(request);
    job.updateRequirements(jobRequirements);

    jobRepository.save(job);
  }
}
