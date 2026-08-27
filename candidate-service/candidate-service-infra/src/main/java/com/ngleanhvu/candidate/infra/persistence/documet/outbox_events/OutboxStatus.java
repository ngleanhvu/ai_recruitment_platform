package com.ngleanhvu.candidate.infra.persistence.documet.outbox_events;

public enum OutboxStatus {
    PENDING,
    PUBLISHED,
    FAILED
}