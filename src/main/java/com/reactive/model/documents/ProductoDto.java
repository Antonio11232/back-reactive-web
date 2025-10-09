package com.reactive.model.documents;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductoDto {
    private String id;

    @NotBlank(message = "El campo 'nombre' es requerido.")
    private String nombre;

    @NotNull(message = "El campo 'precio' es requerido.")
    @Positive(message = "El precio debe ser un número positivo.")
    private Double precio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}