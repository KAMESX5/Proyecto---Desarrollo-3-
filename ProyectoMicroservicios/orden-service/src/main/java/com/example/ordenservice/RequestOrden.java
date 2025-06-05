package com.example.ordenservice;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestOrden {
    private Orden Orden;
    private List<ProductoModel> lstProductos;
    private List<EntregaModel> lstEntrega;
}