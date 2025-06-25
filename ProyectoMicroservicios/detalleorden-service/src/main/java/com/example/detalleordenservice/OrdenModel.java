package com.example.detalleordenservice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter

public class OrdenModel {
    private int idOrden;
    private int idUsuario;
    private String estado;
    private Double total;
    private LocalDate fechaCreacion;
}
