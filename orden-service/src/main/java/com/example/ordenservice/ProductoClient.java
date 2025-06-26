package com.example.ordenservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "producto-service")

public interface ProductoClient {

    @GetMapping("/api/productos/{idProducto}")
    ProductoModel obtenerProducto(@PathVariable int idProducto);
}