package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.job.application.dto.request.JobRequirementRequest;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.job.domain.model.job.JobRequirements;
import com.ngleanhvu.job.domain.repository.JobRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UpdateJobRequirementUseCase(
        JobRepository jobRepository,
        JobMapper jobMapper
) {
    public void execute(JobRequirementRequest request, JobId jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        JobRequirements jobRequirements = jobMapper.toJobRequirements(request);
        job.updateRequirements(jobRequirements);

        jobRepository.save(job);
    }

}
