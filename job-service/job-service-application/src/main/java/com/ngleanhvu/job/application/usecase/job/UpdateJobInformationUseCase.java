package com.ngleanhvu.job.application.usecase.job;

import com.ngleanhvu.job.application.dto.request.CreateJobRequest;
import com.ngleanhvu.job.application.mapper.JobMapper;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.job.domain.model.job.WorkPlace;
import com.ngleanhvu.job.domain.model.job.enums.EmploymentType;
import com.ngleanhvu.job.domain.repository.JobRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record UpdateJobInformationUseCase (
        JobRepository jobRepository,
        JobMapper jobMapper
) {
    public void execute(CreateJobRequest request, JobId jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        EmploymentType employmentType = EmploymentType.from(request.employmentType());
        WorkPlace workPlace = jobMapper.toWorkPlace(request.workplace());

        job.updateInformation(request.title(),
                request.description(),
                employmentType,
                workPlace);

        jobRepository.save(job);
    }
}
