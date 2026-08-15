package com.ngleanhvu.application.infra.persistence.repository;

import com.ngleanhvu.application.infra.persistence.document.application.ApplicationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationMongoRepository extends MongoRepository<ApplicationDocument, String> {
}
