package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.job.application.dto.request.CreateJobRequest;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.repository.JobRepository;
import org.springframework.stereotype.Service;

@Service
public record CreateJobUseCase(
        JobRepository jobRepository,
        JobMapper jobMapper
) {
    public void execute(CreateJobRequest request) {
        Job job = jobMapper.toDomain(request);
        jobRepository.save(job);
    }
}
