package com.ngleanhvu.job.domain.repository;

import com.ngleanhvu.job.domain.model.job.Job;

import java.util.Optional;

public interface JobRepository {
    void save(Job job);
    Optional<Job> findById(String id);
}
