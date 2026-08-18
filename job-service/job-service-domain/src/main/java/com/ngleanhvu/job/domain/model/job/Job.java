package com.ngleanhvu.job.domain.model.job;

import com.ngleanhvu.common.exception.BusinessException;
import com.ngleanhvu.common.util.ValidationUtil;
import com.ngleanhvu.job.domain.model.job.enums.EmploymentType;
import com.ngleanhvu.job.domain.model.job.enums.JobStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public final class Job {
  private JobId jobId;
  private String recruiterId;
  private String title;
  private String description;
  private EmploymentType employmentType;
  private WorkPlace workplace;
  private SalaryRange salary;
  private JobRequirements requirements;
  private List<Benefit> benefits;
  private JobStatus status;
  private ApplicationDeadline deadline;
  private LocalDateTime publishedAt;

  public Job(
      JobId jobId,
      String recruiterId,
      String title,
      String description,
      EmploymentType employmentType,
      WorkPlace workplace,
      SalaryRange salary,
      JobRequirements requirements,
      List<Benefit> benefits,
      JobStatus status,
      ApplicationDeadline deadline,
      LocalDateTime publishedAt) {
    this.jobId = jobId;
    this.recruiterId = recruiterId;
    this.title = title;
    this.description = description;
    this.employmentType = employmentType;
    this.workplace = workplace;
    this.salary = salary;
    this.requirements = requirements;
    this.benefits =
        ValidationUtil.isNotNull(benefits) ? new ArrayList<>(benefits) : new ArrayList<>();
    this.status = status;
    this.deadline = deadline;
    this.publishedAt = publishedAt;
  }

  public static Job create(
      String recruiterId,
      String title,
      String description,
      EmploymentType employmentType,
      WorkPlace workplace) {
    return new Job(
        new JobId(JobId.generate().value()),
        recruiterId,
        title,
        description,
        employmentType,
        workplace,
        null,
        null,
        null,
        JobStatus.DRAFT,
        null,
        null);
  }

  public static Job rehydrate(
      JobId jobId,
      String recruiterId,
      String title,
      String description,
      EmploymentType employmentType,
      WorkPlace workplace,
      SalaryRange salary,
      JobRequirements requirements,
      List<Benefit> benefits,
      JobStatus status,
      ApplicationDeadline deadline,
      LocalDateTime publishedAt) {
    return new Job(
        jobId,
        recruiterId,
        title,
        description,
        employmentType,
        workplace,
        salary,
        requirements,
        benefits,
        status,
        deadline,
        publishedAt);
  }

  public void publish() {
    if (status != JobStatus.DRAFT) throw new BusinessException("Only draft job can be published");

    this.status = JobStatus.PUBLISHED;
    this.publishedAt = LocalDateTime.now();
  }

  public void close() {
    if (status != JobStatus.PUBLISHED)
      throw new BusinessException("Only published job can be closed");
    this.status = JobStatus.CLOSED;
  }

  public void expire() {
    if (status != JobStatus.PUBLISHED) throw new BusinessException("Only published job can expire");

    if (!deadline.isExpired()) throw new BusinessException("Job deadline has not been reached");

    this.status = JobStatus.EXPIRED;
  }

  public void updateInformation(
      String title, String description, EmploymentType employmentType, WorkPlace workplace) {
    if (!ensureEditable()) return;
    this.title = title;
    this.description = description;
    this.employmentType = employmentType;
    this.workplace = workplace;
  }

  public void updateSalary(SalaryRange salary) {
    if (!ensureEditable()) return;
    this.salary = salary;
  }

  public void updateRequirements(JobRequirements requirements) {
    if (!ensureEditable()) return;
    this.requirements = requirements;
  }

  public void updateBenefits(List<Benefit> benefits) {
    if (!ensureEditable()) return;
    this.benefits = List.copyOf(benefits);
  }

  public boolean isAcceptingApplications() {
    return status == JobStatus.PUBLISHED && !deadline.isExpired();
  }

  private boolean ensureEditable() {
    return this.status == JobStatus.DRAFT;
  }
}
