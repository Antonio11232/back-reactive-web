package com.reactive.service.impl;

import com.reactive.model.dao.ProductoDao;
import com.reactive.model.documents.Producto;
import com.reactive.service.IProductoService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoDao productoDao;

    public ProductoServiceImpl(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        producto.setFechaCreacion(LocalDateTime.now());
        return productoDao.save(producto);
    }

    @Override
    public Mono<Producto> findById(String id) {
        return productoDao.findById(id);
    }

    @Override
    public Flux<Producto> findAll() {
        return productoDao.findAll();
    }

}
