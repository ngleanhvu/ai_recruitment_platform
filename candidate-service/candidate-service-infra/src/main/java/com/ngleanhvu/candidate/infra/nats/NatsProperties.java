package com.ngleanhvu.candidate.infra.nats;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "nats")
public record NatsProperties(
        String url,
        String connectionName
) {
}