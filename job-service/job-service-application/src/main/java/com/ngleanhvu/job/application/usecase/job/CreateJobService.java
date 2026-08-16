package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.job.application.dto.request.CreateJobRequest;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.application.port.input.job.CreateJobUseCase;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.application.port.output.JobRepository;
import org.springframework.stereotype.Service;

@Service
public record CreateJobService(
        JobRepository jobRepository,
        JobMapper jobMapper
) implements CreateJobUseCase {
    public void execute(CreateJobRequest request) {
        Job job = jobMapper.toDomain(request);
        jobRepository.save(job);
    }
}
