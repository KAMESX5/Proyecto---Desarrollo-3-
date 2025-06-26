package com.example.detalleordenservice;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class ProductoModel {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String categoria;
    private int stock;
    private int cantidad;
    private LocalDate fechaCreacion;
}
