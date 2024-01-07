package com.example.springmsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // Eureka Server 등록
public class SpringMsaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMsaApplication.class, args);
    }

}
