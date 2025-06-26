package com.example.detalleordenservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "producto-service")
public interface ProductoClient {

    @GetMapping("/api/productos/{idProducto}")
    ProductoModel obtenerProducto(@PathVariable int idProducto);

    @PutMapping("/api/productos/{idProducto}")
    ProductoModel actualizarProducto(@PathVariable int idProducto, @RequestBody ProductoModel producto);
}