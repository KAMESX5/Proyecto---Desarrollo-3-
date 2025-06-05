package com.example.pagoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.pagoservice")

public class PagoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PagoServiceApplication.class, args);
    }

}
