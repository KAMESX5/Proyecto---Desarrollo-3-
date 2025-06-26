package com.example.detalleordenservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleordenRepository extends JpaRepository<Detalleorden, Integer> {
    List<Detalleorden> findByIdOrden(int idOrden);
}
