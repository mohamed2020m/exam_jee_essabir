package com.essabir.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.essabir.exam.entities")
@ComponentScan(basePackages = {"com.essabir.exam.controllers","com.essabir.exam.services"})
@EnableJpaRepositories(basePackages = "com.essabir.exam.repositories")
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

}
