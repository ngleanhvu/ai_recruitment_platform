package com.ngleanhvu.application.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ngleanhvu.application.start",
        "com.ngleanhvu.application.interfaces",
        "com.ngleanhvu.application.application",
        "com.ngleanhvu.application.infra",
        "com.ngleanhvu.common"
})
@EnableMongoRepositories(basePackages = "com.ngleanhvu.application.infra.persistence.repository")
public class StartApplication {
  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class, args);
  }
}
