package com.productoservice.producto_service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Producto findByIdProducto(int idProducto);
}
