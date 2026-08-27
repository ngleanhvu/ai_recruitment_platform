package com.ngleanhvu.candidate.infra.nats;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(NatsProperties.class)
public class NatsConfiguration {
}
