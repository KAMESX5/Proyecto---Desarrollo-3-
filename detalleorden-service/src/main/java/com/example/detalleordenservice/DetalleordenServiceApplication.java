package com.example.detalleordenservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.detalleordenservice")
@EnableRabbit

public class DetalleordenServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DetalleordenServiceApplication.class, args);
    }

}
