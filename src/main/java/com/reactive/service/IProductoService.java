package com.reactive.service;

import com.reactive.model.documents.Producto;
import com.reactive.model.documents.ProductoDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductoService {

    Mono<Producto> save(ProductoDto producto);

    Mono<Producto> findById(String id);
    Flux<Producto> findAll();
    public Mono<Void> deleteById(String id);
}
