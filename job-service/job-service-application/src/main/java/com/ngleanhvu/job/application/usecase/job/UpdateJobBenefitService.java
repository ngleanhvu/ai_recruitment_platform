package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.application.port.input.job.UpdateJobBenefitUseCase;
import com.ngleanhvu.job.application.port.output.JobRepository;
import com.ngleanhvu.job.domain.model.job.Benefit;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public record UpdateJobBenefitService(JobRepository jobRepository, JobMapper jobMapper)
    implements UpdateJobBenefitUseCase {
  public void execute(List<String> request, JobId jobId) {
    Job job =
        jobRepository
            .findById(jobId)
            .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

    List<Benefit> benefits = request.stream().map(jobMapper::toBenefit).toList();

    job.updateBenefits(benefits);

    jobRepository.save(job);
  }
}
