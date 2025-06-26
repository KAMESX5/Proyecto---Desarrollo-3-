package com.example.ordenservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "entrega-service")

public interface EntregaClient {
    @PostMapping("/api/entrega")
    ResponseEntity<String> CrearEntregas(@RequestBody EntregaModel ListaEntrega);
}

