package com.ngleanhvu.job.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(
    basePackages = {
      "com.ngleanhvu.job.start",
      "com.ngleanhvu.job.interfaces.rest",
      "com.ngleanhvu.job.application",
      "com.ngleanhvu.job.infra",
      "com.ngleanhvu.common"
    })
@EnableMongoRepositories(basePackages = "com.ngleanhvu.job.infra.persistence.repository")
public class StartApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
