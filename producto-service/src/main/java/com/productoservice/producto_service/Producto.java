package com.productoservice.producto_service;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
@Getter
@Setter

public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int idProducto;

    private String nombre;

    private String descripcion;

    private Double precio;

    private String categoria;

    @Column(name = "stock_disponible")
    private int stock;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;


}
