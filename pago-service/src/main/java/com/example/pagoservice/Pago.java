package com.example.pagoservice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pagos")
@Getter
@Setter

public class Pago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int idPago;

    @Column(name = "id_orden")
    private int idOrden;

    private String estado;

    @Column(name = "fecha_pago")
    private LocalDate fecha;

    @Column(name = "metodo_pago")
    private String metodoPago;
}
