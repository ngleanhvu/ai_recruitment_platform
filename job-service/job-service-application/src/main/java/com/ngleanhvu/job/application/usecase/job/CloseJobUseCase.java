package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.job.domain.repository.JobRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record CloseJobUseCase (
        JobRepository jobRepository,
        JobMapper jobMapper
) {
    public void execute(JobId jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        job.close();
        jobRepository.save(job);
    }
}
