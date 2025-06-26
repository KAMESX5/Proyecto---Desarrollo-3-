package com.example.detalleordenservice;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class RequestDetalleOrden {
    private OrdenModel orden;
    private List<ProductoModel> lstProductos;
}
