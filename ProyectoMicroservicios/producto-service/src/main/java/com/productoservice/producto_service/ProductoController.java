package com.productoservice.producto_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoRepository repository;

    @Autowired
    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Producto> obtenerTodos() {
        List<Producto> productos = repository.findAll();
        return productos;
    }

    @GetMapping("/{idProducto}")
    public Producto obtenerProducto(@PathVariable int idProducto) {
        Producto producto = repository.findByIdProducto(idProducto);
        return producto;
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        System.out.println("producto");
        System.out.println(producto.getIdProducto());
        return repository.save(producto);
    }

    @PutMapping("/{idProducto}")
    public Producto actualizarProducto(@PathVariable int idProducto, @RequestBody Producto nuevaProducto) {
        return repository.findById(idProducto)
                .map(productoExistente -> {
                    productoExistente.setNombre(nuevaProducto.getNombre());
                    productoExistente.setDescripcion(nuevaProducto.getDescripcion());
                    productoExistente.setPrecio(nuevaProducto.getPrecio());
                    productoExistente.setCategoria(nuevaProducto.getCategoria());
                    productoExistente.setStock(nuevaProducto.getStock());
                    productoExistente.setFechaCreacion(nuevaProducto.getFechaCreacion());
                    Producto productoActualizada = repository.save(productoExistente);
                    return productoActualizada;
                })
                .orElseThrow(() -> new RuntimeException("Producto con ID " + idProducto + " no encontrada"));
    }


    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        repository.deleteById(id);
    }
}
