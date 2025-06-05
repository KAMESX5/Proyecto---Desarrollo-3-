package com.example.pagoservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    private final PagoRepository repository;
    private final OrdenClient ordenClient;

    @Autowired
    public PagoController(PagoRepository repository, OrdenClient ordenClient) {
        this.repository = repository;
        this.ordenClient = ordenClient;
    }

    @GetMapping
    public List<Pago> obtenerTodos() {
        return repository.findAll();
    }

    @GetMapping("/{idPago}")
    public Pago obtenerPorId(@PathVariable int idPago) {
        return repository.findById(idPago).orElse(null);
    }

    @PostMapping
    public Pago crear(@RequestBody RequestPago ModelPago) {
        Pago pagoGuardada = new Pago();

        if (ModelPago == null || ModelPago.getOrden() == null) {
            throw new IllegalArgumentException("El campo 'pago' no puede ser null.");
        }
        else {
            pagoGuardada.setIdOrden(ModelPago.getOrden().getIdOrden());
            pagoGuardada.setEstado("aprobado");
            pagoGuardada.setFecha(LocalDate.now());
            pagoGuardada.setMetodoPago("efectivo");
            pagoGuardada = repository.save(pagoGuardada);

            OrdenModel ordenactualizar = ordenClient.obtenerOrden(ModelPago.getOrden().getIdOrden());
            ordenactualizar.setEstado("pagada");
            ordenClient.actualizarOrden(ModelPago.getOrden().getIdOrden(),ordenactualizar);
        }

        return pagoGuardada;
    }

    @PutMapping("/{idPago}")
    public Pago actualizarOrden(@PathVariable int idPago, @RequestBody Pago nuevaPago) {
        return repository.findById(idPago)
                .map(pagoExistente -> {
                    pagoExistente.setIdOrden(nuevaPago.getIdOrden());
                    pagoExistente.setEstado(nuevaPago.getEstado());
                    pagoExistente.setIdPago(nuevaPago.getIdPago());
                    pagoExistente.setFecha(nuevaPago.getFecha());
                    pagoExistente.setMetodoPago(nuevaPago.getMetodoPago());

                    Pago pagoActualizada = repository.save(pagoExistente);
                    return pagoActualizada;
                })
                .orElseThrow(() -> new RuntimeException("Orden con ID " + idPago + " no encontrada"));
    }


}
