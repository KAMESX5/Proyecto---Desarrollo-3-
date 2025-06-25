package com.example.ordenservice;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RequestOrden {
    private Orden orden;
    private List<ProductoModel> lstProductos;
    private EntregaModel entrega;
}