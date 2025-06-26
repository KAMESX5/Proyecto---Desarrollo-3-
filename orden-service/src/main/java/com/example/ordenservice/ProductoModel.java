package com.example.ordenservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductoModel {
    private int idProducto;
    private String nombre;
    private Double precio;
    private String categoria;
    private int cantidad;
    private String descripcion;
}
