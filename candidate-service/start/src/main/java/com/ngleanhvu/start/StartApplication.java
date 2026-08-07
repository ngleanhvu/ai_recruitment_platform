package com.ngleanhvu.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(
        scanBasePackages = "com.ngleanhvu"
)
@EnableMongoRepositories(
        basePackages = "com.ngleanhvu.infra.persistence.repository"
)
public class StartApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}