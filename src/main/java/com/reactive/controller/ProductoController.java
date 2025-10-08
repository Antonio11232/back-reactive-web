package com.reactive.controller;

import com.reactive.model.documents.Producto;
import com.reactive.service.IProductoService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public Mono<Producto> guardarProducto(@RequestBody Producto producto){
        return productoService.save(producto);
    }

    @GetMapping()
    public Flux<Producto> listarProductos(){
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Producto> buscarProductoId(@PathVariable String id){
        return productoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminarProductoId(@PathVariable String id){
        return productoService.deleteById(id);
    }
}
