package com.ngleanhvu.candidate.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(
    basePackages = {
      "com.ngleanhvu.candidate.start",
      "com.ngleanhvu.candidate.interfaces",
      "com.ngleanhvu.candidate.application",
      "com.ngleanhvu.candidate.infra",
      "com.ngleanhvu.common"
    })
@EnableMongoRepositories(basePackages = "com.ngleanhvu.candidate.infra.persistence.repository")
public class StartApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
