package com.reactive.model.dao;

import com.reactive.model.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductoDao extends ReactiveMongoRepository<Producto,String> {
}
