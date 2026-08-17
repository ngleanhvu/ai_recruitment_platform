package com.ngleanhvu.application.infra.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@RequiredArgsConstructor
public class MongoConfig {

  @Value("${application.mongodb.uri}")
  private String uri;

  @Value("${application.mongodb.database}")
  private String database;

  @Bean
  public MongoClient mongoClient() {
    return MongoClients.create(uri);
  }

  @Bean
  public MongoTemplate mongoTemplate(MongoClient mongoClient) {
    return new MongoTemplate(mongoClient, database);
  }
}
