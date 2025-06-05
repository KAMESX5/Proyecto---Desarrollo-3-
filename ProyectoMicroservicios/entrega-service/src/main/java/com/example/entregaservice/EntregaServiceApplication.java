package com.example.entregaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.entregaservice")

public class EntregaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EntregaServiceApplication.class, args);
    }

}
