package com.ngleanhvu.candidate.infra.nats;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NatsConnectionConfig {

    private final NatsProperties properties;

    @Bean(destroyMethod = "close")
    public Connection natsConnection() throws Exception {

        Options options = new Options.Builder()
                .server(properties.url())
                .connectionName(properties.connectionName())
                .build();

        return Nats.connect(options);
    }
}
