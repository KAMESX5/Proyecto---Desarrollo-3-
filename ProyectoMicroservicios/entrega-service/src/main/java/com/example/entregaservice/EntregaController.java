package com.example.entregaservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/entrega")
public class EntregaController {

    private final OrdenClient ordenClient;
    private final EntregaRepository repository;

    public EntregaController(EntregaRepository repository, OrdenClient ordenClient) {
        this.ordenClient = ordenClient;
        this.repository = repository;
    }

    @GetMapping
    public List<Entrega> obtenerTodos() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody List<RequestOrden> Model) {
        List<Entrega> lista = new LinkedList<Entrega>();
        for (RequestOrden Obj : Model)
        {
            OrdenModel Orden = ordenClient.obtenerOrden(Obj.getOrden().getIdOrden());
            if(Orden != null){
                Entrega entrega = new Entrega();
                entrega.setIdOrden(Orden.getIdOrden());
                entrega.setIdRepartidor(Obj.getEntrega().getIdRepartidor());
                entrega.setEstadoEntrega(Obj.getEntrega().getEstadoEntrega());
                entrega.setUbicacionActual(Obj.getEntrega().getUbicacionActual());
                entrega.setHoraInicio(Obj.getEntrega().getHoraInicio());
                entrega.setHoraEntrega(Obj.getEntrega().getHoraEntrega());
                lista.add(entrega);
            }
            else {
                return  ResponseEntity.ok("La Orden no puede ser null.");
            }
        }

        var lista2 = repository.saveAll(lista);


        return ResponseEntity.ok(lista2);
    }

    @PutMapping("/{identrega}")
    public Entrega actualizar(@PathVariable int identrega,@RequestBody Entrega Model) {
        return repository.findById(identrega)
                .map(entregaExistente -> {
                    entregaExistente.setIdOrden(Model.getIdOrden());
                    entregaExistente.setIdRepartidor(Model.getIdRepartidor());
                    entregaExistente.setEstadoEntrega(Model.getEstadoEntrega());
                    entregaExistente.setUbicacionActual(Model.getUbicacionActual());
                    entregaExistente.setHoraInicio(LocalDateTime.now());
                    entregaExistente.setHoraEntrega(LocalDateTime.now().plusHours(2));
                    Entrega entregaActualizada = repository.save(entregaExistente);

                    return entregaActualizada;
                })
                .orElseThrow(() -> new RuntimeException("Entrega con ID " + identrega + " no encontrada"));
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repository.deleteById(id);
    }

}

