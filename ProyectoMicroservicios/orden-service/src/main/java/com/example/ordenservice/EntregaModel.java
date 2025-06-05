package com.example.ordenservice;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class EntregaModel {

    private int idOrden;
    private int idRepartidor;

    //'pendiente' 'en_camino' 'entregado' 'fallido'
    private String estadoEntrega;
    private String ubicacionActual;
    private LocalDateTime horaInicio;
    private LocalDateTime horaEntrega;
}
