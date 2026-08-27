package com.ngleanhvu.candidate.infra.persistence.repository;

import com.ngleanhvu.candidate.infra.persistence.documet.outbox_events.OutboxEventDocument;
import com.ngleanhvu.candidate.infra.persistence.documet.outbox_events.OutboxStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface OutboxEventMongoRepository
        extends MongoRepository<OutboxEventDocument, String> {

    List<OutboxEventDocument> findTop100ByStatusAndNextRetryAtLessThanEqualOrderByCreatedAtAsc(
            OutboxStatus status,
            Instant now
    );

    List<OutboxEventDocument> findTop100ByStatusOrderByCreatedAtAsc(
            OutboxStatus status
    );
}
