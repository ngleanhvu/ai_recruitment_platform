package com.ngleanhvu.candidate.infra.persistence.repository;

import com.ngleanhvu.candidate.infra.persistence.documet.resume.ResumeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeMongoRepository extends MongoRepository<ResumeDocument, String> {
    List<ResumeDocument> findByCandidateIdOrderByVersionDesc(
            String candidateId
    );

    Optional<ResumeDocument> findFirstByCandidateIdOrderByVersionDesc(
            String candidateId
    );

    int countByCandidateId(String candidateId);
}
