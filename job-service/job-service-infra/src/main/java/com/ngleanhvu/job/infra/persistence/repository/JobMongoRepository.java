package com.ngleanhvu.job.infra.persistence.repository;

import com.ngleanhvu.job.infra.persistence.document.job.JobDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobMongoRepository extends MongoRepository<JobDocument, String> {}
