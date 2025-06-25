package com.example.entregaservice;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "entrega")

public class Entrega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrega")
    private int idEntrega;

    @Column(name = "id_orden")
    private int idOrden;

    @Column(name = "id_repartidor")
    private int idRepartidor;

    //'pendiente' 'en_camino' 'entregado' 'fallido'
    @Column(name = "estado_entrega")
    private String estadoEntrega;

    @Column(name = "ubicacion_actual")
    private String ubicacionActual;

    @Column(name = "hora_inicio")
    private LocalDateTime horaInicio;

    @Column(name = "hora_entrega")
    private LocalDateTime horaEntrega;

}
