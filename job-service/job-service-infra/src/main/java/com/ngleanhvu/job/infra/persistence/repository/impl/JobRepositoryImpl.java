package com.ngleanhvu.job.infra.persistence.repository.impl;

import com.ngleanhvu.job.application.port.output.JobRepository;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.JobId;
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
    public Optional<Job> findById(JobId id) {
        return jobMongoRepository.findById(id.value()).map(jobDocumentMapper::toDomain);
    }

    @Override
    public boolean existsById(JobId jobId) {
        return jobMongoRepository.existsById(jobId.value());
    }
}
