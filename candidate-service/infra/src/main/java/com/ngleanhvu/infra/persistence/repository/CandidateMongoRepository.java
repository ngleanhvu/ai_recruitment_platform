package com.ngleanhvu.infra.persistence.repository;

import com.ngleanhvu.infra.persistence.documet.candidate.CandidateDocument;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateMongoRepository extends MongoRepository<CandidateDocument, String> {

  boolean existsByEmail(String email);

  Optional<CandidateDocument> findByEmail(String email);

  @Query(value = "{ 'profile.phone': ?0 }", exists = true)
  boolean existsByPhone(String phone);
}
