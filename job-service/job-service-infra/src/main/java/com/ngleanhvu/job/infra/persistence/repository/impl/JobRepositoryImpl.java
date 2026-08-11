package com.ngleanhvu.job.infra.persistence.repository.impl;

import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.repository.JobRepository;
import com.ngleanhvu.job.infra.persistence.document.job.JobDocument;
import com.ngleanhvu.job.infra.persistence.mapper.JobDocumentMapper;
import com.ngleanhvu.job.infra.persistence.repository.JobMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class JobRepositoryImpl implements JobRepository {

    private final JobMongoRepository jobMongoRepository;
    private final JobDocumentMapper jobDocumentMapper;

    @Override
    public void save(Job job) {
        JobDocument jobDocument = jobDocumentMapper.toDocument(job);
        jobMongoRepository.save(jobDocument);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Job> findById(String id) {
        return Optional.empty();
    }
}
