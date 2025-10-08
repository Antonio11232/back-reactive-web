package com.reactive.model.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "productos")
public class Producto {

    @Id
    private String  id;
    private String nombre;
    private Double precio;
    private Date fechaCreacion;
}
