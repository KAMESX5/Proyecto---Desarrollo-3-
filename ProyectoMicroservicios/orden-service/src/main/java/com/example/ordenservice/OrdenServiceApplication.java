package com.example.ordenservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.ordenservice")
@EnableRabbit

public class OrdenServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrdenServiceApplication.class, args);
    }

}
