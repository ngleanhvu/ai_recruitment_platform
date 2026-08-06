package com.ngleanhvu.infra.persistence.repository;

import com.ngleanhvu.infra.persistence.documet.candidate.CandidateDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateMongoRepository extends MongoRepository<CandidateDocument, String> {
}
