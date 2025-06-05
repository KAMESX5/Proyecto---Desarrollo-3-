package com.example.ordenservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ruta-service")

public interface EntregaClient {
    @PostMapping("/api/entrega")
    ResponseEntity<String> CrearEntregas(@RequestBody List<EntregaModel> ListaEntrega);
}

