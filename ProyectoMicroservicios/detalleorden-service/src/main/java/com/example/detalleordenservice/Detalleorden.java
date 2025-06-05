package com.example.detalleordenservice;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalleorden")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Detalleorden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalleorden")
    private int idDetalleorden;

    @Column(name = "id_orden")
    private int idOrden;

    @Column(name = "id_producto")
    private int idProducto;

    private int cantidad;

    private double precio;
}

