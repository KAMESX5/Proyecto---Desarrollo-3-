package com.example.ordenservice;

import com.example.ordenservice.rabbitmq.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orden")
public class OrdenController {

    @Autowired
    private RabbitMQSender rabbitMQSender;
    private final OrdenRepository repository;
    private final ProductoClient productoClient;
    private final DetalleOrdenClient detalleOrdenClient;
    private final EntregaClient entregaClient;

    @Autowired
    public OrdenController(
        OrdenRepository repository, ProductoClient productoClient,
        DetalleOrdenClient detalleOrdenClient, EntregaClient entregaClient
    )
    {
        this.repository = repository;
        this.productoClient = productoClient;
        this.detalleOrdenClient = detalleOrdenClient;
        this.entregaClient = entregaClient;
    }

    @GetMapping
    public List<Orden> obtenerTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Orden obtenerPorId(@PathVariable int id) {
        System.out.println("idbusqueda:" + id);
        var respuesta = repository.findById(id).orElse(null);
        System.out.println("encontrado:" + respuesta.getIdOrden());

        return respuesta;
    }

    public ResponseEntity<?> CrearDetalleOrden(RequestOrden ModelRequestOrden) {
        List<ProductoModel> lst = new ArrayList<>();

        for (ProductoModel item : ModelRequestOrden.getLstProductos())
        {
            ProductoModel producto = productoClient.obtenerProducto(item.getIdProducto());
            producto.setCantidad(item.getCantidad());
            producto.setPrecio(item.getPrecio());
            lst.add(producto);
        }

        ModelRequestOrden.setLstProductos(lst);
        ResponseEntity<String> response = detalleOrdenClient.CrearDetalle(ModelRequestOrden);
        return response;
    }

    public ResponseEntity<?> CrearEntrega(EntregaModel Model) {
        ResponseEntity<String> response = entregaClient.CrearEntregas(Model);
        return response;
    }

    @PostMapping
    public Orden crear(@RequestBody RequestOrden ModelOrden) {
        RequestOrden ModelRequestOrden = new RequestOrden();

        if (ModelOrden == null || ModelOrden.getOrden() == null) {
            throw new IllegalArgumentException("El campo 'orden' no puede ser null.");
        }else {
            Orden ordenGuardada = repository.save(ModelOrden.getOrden());
            ModelRequestOrden.setOrden(ordenGuardada);

            if(CrearDetalleOrden(ModelOrden).getStatusCode() != HttpStatus.OK){
                eliminar(ordenGuardada.getIdOrden());
            }

            if(ModelOrden.getEntrega() != null){
                ModelOrden.getEntrega().setIdOrden(ordenGuardada.getIdOrden());
                CrearEntrega(ModelOrden.getEntrega());
            }
        }

        return ModelOrden.getOrden();
    }

    @PostMapping("/rb")
    public Orden crearRb(@RequestBody RequestOrden ModelOrden) {
        Orden ordenGuardada = repository.save(ModelOrden.getOrden());
        ModelOrden.setOrden(ordenGuardada);

        rabbitMQSender.enviarCreateDetalle(ModelOrden);

        if (ModelOrden.getEntrega() != null) {
            ModelOrden.getEntrega().setIdOrden(ordenGuardada.getIdOrden());
            CrearEntrega(ModelOrden.getEntrega());
        }

        return ordenGuardada;
    }

    @PutMapping("/{idOrden}")
    public Orden actualizarOrden(@PathVariable int idOrden, @RequestBody Orden nuevaOrden) {
        return repository.findById(idOrden)
                .map(ordenExistente -> {
                    ordenExistente.setIdUsuario(nuevaOrden.getIdUsuario());
                    ordenExistente.setEstado(nuevaOrden.getEstado());
                    ordenExistente.setTotal(nuevaOrden.getTotal());
                    ordenExistente.setFechaCreacion(nuevaOrden.getFechaCreacion());

                    Orden ordenActualizada = repository.save(ordenExistente);
                    return ordenActualizada;
                })
                .orElseThrow(() -> new RuntimeException("Orden con ID " + idOrden + " no encontrada"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repository.deleteById(id);
    }
}
