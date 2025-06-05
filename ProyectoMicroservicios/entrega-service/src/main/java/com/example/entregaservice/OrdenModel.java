package com.example.entregaservice;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter

public class OrdenModel {
    private int idOrden;
    private int idUsuario;
    private String estado;
    private LocalDate fechaCreacion;
}
