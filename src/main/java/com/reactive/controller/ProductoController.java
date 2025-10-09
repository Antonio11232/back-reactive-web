package com.reactive.controller;

import com.reactive.model.documents.Producto;
import com.reactive.model.documents.ProductoDto;
import com.reactive.service.IProductoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Mono<Producto>> guardarProducto(@Valid @RequestBody ProductoDto productoDto) {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(productoService.save(productoDto));
    }

    @GetMapping()
    public ResponseEntity<Flux<Producto>> listarProductos() {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Producto>> buscarProductoId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(productoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Void>> eliminarProductoId(@PathVariable String id) {
        return ResponseEntity.status(HttpStatusCode.valueOf(204)).body(productoService.deleteById(id));
    }
}
