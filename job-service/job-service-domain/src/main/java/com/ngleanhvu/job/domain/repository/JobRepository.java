package com.ngleanhvu.job.domain.repository;

import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;

import java.util.Optional;

public interface JobRepository {
    void save(Job job);
    Optional<Job> findById(JobId id);
}
