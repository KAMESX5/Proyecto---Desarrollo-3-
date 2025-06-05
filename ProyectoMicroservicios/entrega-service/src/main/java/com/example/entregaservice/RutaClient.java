package com.example.entregaservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ruta-service")
public interface RutaClient {

    @GetMapping("/api/ruta/{idRuta}")
    RutaModel obtener(@PathVariable int idRuta);

    @PutMapping("/api/ruta/{idRuta}")
    RutaModel actualizar(@PathVariable int idRuta, RutaModel ruta);

}

