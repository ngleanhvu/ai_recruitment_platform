package com.ngleanhvu.candidate.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.ngleanhvu.candidate.start")
@EnableMongoRepositories(basePackages = "com.ngleanhvu.candidate.infra.persistence.repository")
public class StartApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
