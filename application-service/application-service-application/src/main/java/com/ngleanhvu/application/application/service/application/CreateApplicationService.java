package com.ngleanhvu.application.application.service.application;

import com.ngleanhvu.application.application.dto.request.CreateApplicationRequest;
import com.ngleanhvu.application.application.mapper.ApplicationMapper;
import com.ngleanhvu.application.application.port.input.application.CreateApplicationUseCase;
import com.ngleanhvu.application.application.port.output.application.ApplicationRepository;
import com.ngleanhvu.application.application.port.output.candidate.CandidateGateway;
import com.ngleanhvu.application.application.port.output.candidate.ResumeGateway;
import com.ngleanhvu.application.application.port.output.job.JobGateway;
import com.ngleanhvu.application.domain.model.application.Application;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public record CreateApplicationService(
    CandidateGateway candidateGateway,
    ApplicationRepository applicationRepository,
    ResumeGateway resumeGateway,
    JobGateway jobGateway,
    ApplicationMapper applicationMapper)
    implements CreateApplicationUseCase {
  @Override
  public void execute(CreateApplicationRequest request) {
    boolean existResume =
        resumeGateway.existsResumeByIdAndCandidateId(request.resumeId(), request.candidateId());
    if (!existResume) throw new ResourceNotFoundException("Resume not found");
    boolean existCandidate = candidateGateway.existsCandidateById(request.candidateId());
    if (!existCandidate) throw new ResourceNotFoundException("Candidate not found");
    boolean existJob = jobGateway().existsJobById(request.jobId());
    if (!existJob) throw new ResourceNotFoundException("Job not found");
    Application application = applicationMapper.toDomain(request);
    applicationRepository.save(application);
  }
}
