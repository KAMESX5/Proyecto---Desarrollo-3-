package com.example.pagoservice;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrdenModel {
    private int idOrden;
    private int idUsuario;
    private String estado;
    private Double total;
    private LocalDate fechaCreacion;

}
