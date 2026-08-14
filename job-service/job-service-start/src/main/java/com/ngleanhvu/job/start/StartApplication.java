package com.ngleanhvu.job.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.ngleanhvu.job")
@EnableMongoRepositories(basePackages = "com.ngleanhvu.job.infra.persistence.repository")
public class StartApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
