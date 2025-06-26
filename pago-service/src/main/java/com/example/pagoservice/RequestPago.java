package com.example.pagoservice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestPago {
    private OrdenModel Orden;
    private Pago Pago;
}
