package com.reactive.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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


@Service
public class ProductoServiceImpl implements IProductoService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProductoServiceImpl.class);

    private final ProductoDao productoDao;
    private final ObjectMapper objectMapper;

    public ProductoServiceImpl(ProductoDao productoDao, ObjectMapper objectMapper) {
        this.productoDao = productoDao;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Producto> save(Producto producto) {
        producto.setFechaCreacion(LocalDateTime.now());
        return productoDao.save(producto)
                .doOnNext(producto1 -> buildLog("Producto guardado exitosamente.", producto1));
    }

    @Override
    public Mono<Producto> findById(String id) {
        return productoDao.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Recurso no encontrado. ID["+id+"]")))
                .doOnNext(producto -> buildLog("Producto consultado exitosamente.", producto));
    }

    @Override
    public Flux<Producto> findAll() {
        return productoDao.findAll()
                .collectList()
                .doOnNext(productos -> {
                    try{
                        String productosJson = objectMapper.writeValueAsString(productos);
                        LOGGER.info("Productos consultados exitosamente: {}", productosJson);

                    }catch (JsonProcessingException e){
                        LOGGER.error("Error al serializar el listado de productos a JSON: {}", productos, e);
                    }
                }).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        Mono<Producto> productoDB = productoDao.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Recurso no encontrado. ID["+id+"]")))
                .doOnNext(producto -> buildLog("Producto eliminado exitosamente.",producto));
        return productoDB.flatMap(producto -> productoDao.deleteById(producto.getId()));
    }

    public void buildLog(String message,Producto producto){
        try {
            String productoJson = objectMapper.writeValueAsString(producto);
            LOGGER.info("{} ID [{}] DETALLE {}",message, producto.getId(), productoJson);
        } catch (JsonProcessingException e) {
            LOGGER.error("Error al serializar el producto con ID [{}] a JSON", producto.getId(), e);
        }
    }


}
