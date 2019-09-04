package com.algalopez.ranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RankingApplication {

    public static void main(String... args) {

        SpringApplication.run(RankingApplication.class, args);
    }
}
