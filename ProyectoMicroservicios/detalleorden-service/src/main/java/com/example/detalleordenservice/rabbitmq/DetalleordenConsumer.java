package com.example.detalleordenservice.rabbitmq;

import com.example.detalleordenservice.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DetalleordenConsumer {

    @Autowired
    private RabbitMQSender rabbitMQSender;

    private final DetalleordenRepository repository;
    private final ProductoClient productoClient;
    private final OrdenClient ordenClient;

    public DetalleordenConsumer(
            DetalleordenRepository repository,
            ProductoClient productoClient,
            OrdenClient ordenClient)
    {
        this.repository = repository;
        this.productoClient = productoClient;
        this.ordenClient = ordenClient;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUECREATE)
    public void recibirMensaje(RequestDetalleOrden Model) {
        List<Detalleorden> lista = new ArrayList<>();

        for (ProductoModel item : Model.getLstProductos())
        {
            ProductoModel producto = productoClient.obtenerProducto(item.getIdProducto());
            if (producto == null || producto.getStock() < item.getCantidad())
            {
                System.out.println("Stock insuficiente o producto no encontrado: ID " + item.getIdProducto());
                return; // Podrías manejar errores de otra forma si quieres reintentos
            }

            Detalleorden DetalleModel = new Detalleorden();
            DetalleModel.setIdOrden(Model.getOrden().getIdOrden());
            DetalleModel.setIdProducto(item.getIdProducto());
            DetalleModel.setCantidad(item.getCantidad());
            DetalleModel.setPrecio(item.getPrecio());
            lista.add(DetalleModel);

            producto.setStock(producto.getStock() - item.getCantidad());

            RequestProducto ModelRabbitProducto = new RequestProducto();
            ModelRabbitProducto.setIdProducto(item.getIdProducto());
            ModelRabbitProducto.setProducto(producto);
            rabbitMQSender.enviarUpdateProducto(ModelRabbitProducto);
        }

        repository.saveAll(lista);

        OrdenModel Orden = ordenClient.obtenerOrden(Model.getOrden().getIdOrden());
        Orden.setTotal(lista.stream()
                .mapToDouble(det -> det.getPrecio() * det.getCantidad())
                .sum());

        ordenClient.actualizarOrden(Model.getOrden().getIdOrden(), Orden);

        System.out.println("Detalles de orden creados exitosamente por consumer.");
    }
}
