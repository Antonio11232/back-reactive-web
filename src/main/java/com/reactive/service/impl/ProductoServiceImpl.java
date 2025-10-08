package com.reactive.service.impl;

import com.reactive.exception.NotFoundException;
import com.reactive.model.dao.ProductoDao;
import com.reactive.model.documents.Producto;
import com.reactive.service.IProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;


@Service
public class ProductoServiceImpl implements IProductoService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductoServiceImpl.class);

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
        return productoDao.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("El producto no existe.")));
    }

    @Override
    public Flux<Producto> findAll() {
        return productoDao.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id){
        Mono<Producto> productoDB = productoDao.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("El producto no existe.")));
        return productoDB.flatMap(producto -> productoDao.deleteById(producto.getId()));
    }


}
