package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.domain.model.job.Benefit;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.job.domain.repository.JobRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UpdateJobBenefitUseCase (
        JobRepository jobRepository,
        JobMapper jobMapper
) {
    public void execute(List<String> request, JobId jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        List<Benefit> benefits = request.stream()
                .map(jobMapper::toBenefit)
                .toList();

        job.updateBenefits(benefits);

        jobRepository.save(job);
    }

}
