package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.job.application.dto.request.SalaryRangeRequest;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.application.port.input.job.UpdateJobSalaryUseCase;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.job.domain.model.job.SalaryRange;
import com.ngleanhvu.job.application.port.output.JobRepository;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record UpdateJobSalaryService(
        JobRepository jobRepository,
        JobMapper jobMapper) implements UpdateJobSalaryUseCase {
    public void execute(SalaryRangeRequest request, JobId jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        SalaryRange salaryRange = jobMapper.toSalaryRange(request);

        job.updateSalary(salaryRange);

        jobRepository.save(job);
    }
}
