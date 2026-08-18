package com.ngleanhvu.application.infra.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CandidateGrpcConfig {

  @Bean
  public ManagedChannel candidateManagedChannel(
      @Value("${candidate.grpc.host}") String host, @Value("${candidate.grpc.port}") int port) {
    return ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
  }
}
