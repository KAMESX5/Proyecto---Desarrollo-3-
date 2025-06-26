package com.productoservice.producto_service.rabbitmq;

import com.productoservice.producto_service.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProductoConsumer {

    private final ProductoRepository repository;

    public ProductoConsumer(
            ProductoRepository repository
    )
    {
        this.repository = repository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUEUPDATE)
    public void recibirMensaje(RequestProducto Modelo) {
        repository.findById(Modelo.getIdProducto())
                .map(productoExistente -> {
                    productoExistente.setNombre(Modelo.getProducto().getNombre());
                    productoExistente.setDescripcion(Modelo.getProducto().getDescripcion());
                    productoExistente.setPrecio(Modelo.getProducto().getPrecio());
                    productoExistente.setCategoria(Modelo.getProducto().getCategoria());
                    productoExistente.setStock(Modelo.getProducto().getStock());
                    productoExistente.setFechaCreacion(Modelo.getProducto().getFechaCreacion());
                    Producto productoActualizada = repository.save(productoExistente);
                    return productoActualizada;
                })
                .orElseThrow(() -> new RuntimeException("Producto con ID " + Modelo.getIdProducto() + " no encontrada"));
    }
}
