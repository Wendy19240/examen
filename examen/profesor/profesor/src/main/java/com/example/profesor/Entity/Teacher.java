package com.example.profesor.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long idProfesor;

    @NotNull(message = "El nombre del miembro es obligatorio")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre solo puede contener letras y espacios")
    @Column(name = "nombre_profesor")
    private String nombreProfesor;

    @NotNull(message = "El instrumento es obligatorio")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El instrumento solo puede contener letras y espacios")
    @Column(name = "instrumento")
    private String instrumento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de nacimiento debe estar en el formato yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;

    @CreationTimestamp
    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}
