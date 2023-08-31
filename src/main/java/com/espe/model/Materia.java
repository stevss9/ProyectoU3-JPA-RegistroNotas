package com.espe.model;

import jakarta.persistence.*;
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "materias") // Indica el nombre de la tabla en la base de datos
public class Materia {
    @Id // Indica que el siguiente atributo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de la clave primaria
    private Long id;

    @Column // Indica que el siguiente atributo es una columna en la tabla
    private String nombre;

    @Column // Indica que el siguiente atributo es una columna en la tabla
    private long usuario;

    @Column // Indica que el siguiente atributo es una columna en la tabla
    private long curso;

    // Métodos getter y setter para los atributos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }

    public long getCurso() {
        return curso;
    }

    public void setCurso(long curso) {
        this.curso = curso;
    }
}