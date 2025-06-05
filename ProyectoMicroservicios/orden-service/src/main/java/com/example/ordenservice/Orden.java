package com.example.ordenservice;

import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden")
    private int idOrden;

    @Column(name = "id_usuario")
    private int idUsuario;

    //'pendiente' 'pagada' 'enviada' 'entregada' 'cancelada'
    private String estado;

    private Double total;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

}
