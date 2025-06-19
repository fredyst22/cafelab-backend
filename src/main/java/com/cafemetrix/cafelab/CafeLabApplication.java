package com.cafemetrix.cafelab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CafeLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(CafeLabApplication.class, args);
    }

}
