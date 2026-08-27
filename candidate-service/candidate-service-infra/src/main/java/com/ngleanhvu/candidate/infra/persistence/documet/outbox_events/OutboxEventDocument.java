package com.ngleanhvu.candidate.infra.persistence.documet.outbox_events;

import com.ngleanhvu.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "outbox_events")
@CompoundIndexes({
        @CompoundIndex(
                name = "idx_outbox_status_retry_created",
                def = "{'status': 1, 'next_retry_at': 1, 'created_at': 1}"
        ),
        @CompoundIndex(
                name = "idx_outbox_aggregate",
                def = "{'aggregate_type': 1, 'aggregate_id': 1}"
        )
})
public class OutboxEventDocument extends BaseEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String eventId;

    private String aggregateType;
    private String aggregateId;
    private String eventType;
    private Map<String, Object> payload;
    private OutboxStatus status;
    private int retryCount;
    private Instant nextRetryAt;
    private String lastError;
    private Instant publishedAt;
}