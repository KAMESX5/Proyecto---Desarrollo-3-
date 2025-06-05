package com.example.detalleordenservice;

import com.example.detalleordenservice.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/detalleorden")
public class DetalleordenController {

    private final DetalleordenRepository repository;
    private final ProductoClient productoClient;
    private final OrdenClient ordenClient;

    public DetalleordenController(
            DetalleordenRepository repository,
            ProductoClient productoClient,
            OrdenClient ordenClient)
    {
        this.repository = repository;
        this.productoClient = productoClient;
        this.ordenClient = ordenClient;
    }

    @GetMapping
    public List<Detalleorden> obtenerTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody RequestDetalleOrden Model) {
        List<Detalleorden> lista = new ArrayList<>();
        for (ProductoModel item : Model.getLstProductos())
        {
            System.out.println(item.getIdProducto());
            ProductoModel producto = productoClient.obtenerProducto(item.getIdProducto());
            if (producto == null || producto.getStock() < item.getCantidad())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Producto no disponible o stock insuficiente para el ID: " + item.getIdProducto());
            }
            else{
                Detalleorden DetalleModel = new Detalleorden();
                DetalleModel.setIdOrden(Model.getOrden().getIdOrden());
                DetalleModel.setIdProducto(item.getIdProducto());
                DetalleModel.setCantidad(item.getCantidad());
                DetalleModel.setPrecio(item.getPrecio());
                lista.add(DetalleModel);

                producto.setStock(producto.getStock() - item.getCantidad());
                productoClient.actualizarProducto(producto.getIdProducto(), producto);
            }
        }

        repository.saveAll(lista);

        OrdenModel Orden = ordenClient.obtenerOrden(Model.getOrden().getIdOrden());
        Orden.setTotal(lista.stream()
                .mapToDouble(det -> det.getPrecio() * det.getCantidad())
                .sum());

        ordenClient.actualizarOrden(Model.getOrden().getIdOrden(), Orden);

        return ResponseEntity.ok("Detalle orden creado correctamente");
    }

    @PutMapping("/{iddetalleorden}")
    public Detalleorden actualizar(@PathVariable int iddetalleorden,@RequestBody Detalleorden Model) {
        return repository.findById(iddetalleorden)
                .map(detalleExistente -> {
                    detalleExistente.setIdProducto(Model.getIdProducto());
                    detalleExistente.setIdOrden(Model.getIdOrden());

                    ProductoModel producto = productoClient.obtenerProducto(Model.getIdProducto());

                    if(detalleExistente.getCantidad() > Model.getCantidad()){
                        var Sobrante = detalleExistente.getCantidad() - Model.getCantidad();
                        producto.setStock(producto.getStock() + Sobrante);
                        productoClient.actualizarProducto(producto.getIdProducto(), producto);
                    }
                    else if(detalleExistente.getCantidad() < Model.getCantidad())
                    {
                        var Sobrante = Model.getCantidad() - detalleExistente.getCantidad();
                        producto.setStock(producto.getStock() - Sobrante);
                        productoClient.actualizarProducto(producto.getIdProducto(), producto);
                    }

                    detalleExistente.setCantidad(Model.getCantidad());
                    detalleExistente.setPrecio(Model.getPrecio());

                    Detalleorden detalleActualizada = repository.save(detalleExistente);

                    List<Detalleorden> lst = repository.findByIdOrden(Model.getIdOrden());
                    OrdenModel Orden = ordenClient.obtenerOrden(Model.getIdOrden());

                    Orden.setTotal(
                            lst.stream()
                                    .mapToDouble(det -> det.getPrecio() * det.getCantidad())
                                    .sum()
                    );

                    ordenClient.actualizarOrden(Orden.getIdOrden(), Orden);

                    return detalleActualizada;
                })
                .orElseThrow(() -> new RuntimeException("Detalle con ID " + iddetalleorden + " no encontrada"));
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repository.deleteById(id);
    }
}

