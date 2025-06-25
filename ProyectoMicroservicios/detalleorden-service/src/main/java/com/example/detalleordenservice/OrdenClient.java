package com.example.detalleordenservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "orden-service")
public interface OrdenClient {

    @GetMapping("/api/orden/{idOrden}")
    OrdenModel obtenerOrden(@PathVariable int idOrden);

    @PutMapping("/api/orden/{idOrden}")
    OrdenModel actualizarOrden(@PathVariable int idOrden, @RequestBody OrdenModel orden);
}
