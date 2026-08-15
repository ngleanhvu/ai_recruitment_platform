package com.ngleanhvu.job.infra.persistence.mapper;

import com.ngleanhvu.job.domain.model.job.ApplicationDeadline;
import com.ngleanhvu.job.domain.model.job.Benefit;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
import com.ngleanhvu.job.domain.model.job.enums.EmploymentType;
import com.ngleanhvu.job.domain.model.job.enums.JobStatus;
import com.ngleanhvu.job.infra.persistence.document.job.JobDocument;
import com.ngleanhvu.common.util.ValidationUtil;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class JobDocumentMapper {
    public JobDocument toDocument(Job job) {
        if (ValidationUtil.isNull(job)) {
            return null;
        }
        JobDocument jobDocument = new JobDocument();
        jobDocument.setId(job.getJobId().value());
        jobDocument.setTitle(job.getTitle());
        jobDocument.setDescription(job.getDescription());
        jobDocument.setRecruiterId(job.getRecruiterId());
        jobDocument.setWorkplace(job.getWorkplace());
        jobDocument.setEmploymentType(job.getEmploymentType().name());

        if (!ValidationUtil.isNull(job.getBenefits())) {
            List<String> benefits = job.getBenefits().stream()
                    .map(Benefit::name)
                    .toList();
            jobDocument.setBenefits(benefits);
        }

        if (!ValidationUtil.isNull(job.getRequirements()))
            jobDocument.setRequirements(job.getRequirements());

        if (!ValidationUtil.isNull(job.getSalary()))
            jobDocument.setSalary(job.getSalary());

        jobDocument.setStatus(job.getStatus().name());

        if (!ValidationUtil.isNull(job.getPublishedAt()))
            jobDocument.setPublishedAt(job.getPublishedAt());

        if (!ValidationUtil.isNull(job.getDeadline()))
            jobDocument.setApplicationDeadline(job.getDeadline().value());

        return jobDocument;
    }

    public Job toDomain(JobDocument jobDocument) {
        List<Benefit> benefits = jobDocument.getBenefits().stream()
                .map(Benefit::new)
                .toList();
        ApplicationDeadline deadline = !ValidationUtil.isNull(jobDocument.getApplicationDeadline())
                ? new ApplicationDeadline(jobDocument.getApplicationDeadline())
                : null;
        return new Job(
                new JobId(jobDocument.getId()),
                jobDocument.getRecruiterId(),
                jobDocument.getTitle(),
                jobDocument.getDescription(),
                EmploymentType.valueOf(jobDocument.getEmploymentType()),
                jobDocument.getWorkplace(),
                jobDocument.getSalary(),
                jobDocument.getRequirements(),
                benefits,
                JobStatus.valueOf(jobDocument.getStatus()),
                deadline,
                jobDocument.getPublishedAt());
    }
}
