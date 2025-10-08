package com.reactive.controller;

import com.reactive.model.documents.Producto;
import com.reactive.model.documents.ProductoDto;
import com.reactive.service.IProductoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public Mono<Producto> guardarProducto(@RequestBody ProductoDto productoDto) {
        return productoService.save(productoDto);
    }

    @GetMapping()
    public Flux<Producto> listarProductos() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Producto> buscarProductoId(@PathVariable String id) {
        return productoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> eliminarProductoId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).body(productoService.deleteById(id));
    }
}
