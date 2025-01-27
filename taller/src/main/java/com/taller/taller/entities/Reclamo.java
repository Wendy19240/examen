package com.taller.taller.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
@Table(name = "Reclamos")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(max = 63, min = 2, message = "El nombre debe tener entre 2 y 63 caracteres.")
    private String cliente;

    @NotBlank(message = "El código no puede estar vacío.")
    @Size(max = 10, message = "El código debe tener como máximo 10 caracteres.")
    private String descripcion;

    @Min(value = 1, message = "Los créditos deben ser al menos 1.")
    @Max(value = 10, message = "Los créditos no pueden ser mayores a 10.")
    private int prioridad;

    @NotNull(message = "La fecha no puede ser nula.")
    @PastOrPresent(message = "La fecha debe ser hoy o una fecha pasada.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaReclamo;

    public Reclamo() {
    }

    public Reclamo(String cliente, String descripcion, int prioridad, Date fechaReclamo) {
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaReclamo = fechaReclamo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public Date getFechaReclamo() {
        return fechaReclamo;
    }

    public void setFechaReclamo(Date fechaReclamo) {
        this.fechaReclamo = fechaReclamo;
    }


}