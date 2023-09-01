package com.espe.model;
//importar librerias 
import jakarta.persistence.*;
@Entity // Indica que la clase es una entidad JPA
@Table(name = "notas") // Indica el nombre de la tabla en la base de datos
public class Nota {
    @Id // Indica que el siguiente atributo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el valor de la clave primaria
    private Long id;

    @Column // Indica que el siguiente atributo es una columna en la tabl
    private double nota;

    @Column // Indica que el siguiente atributo es una columna en la tabla
    private long materia;

    @Column // Indica que el siguiente atributo es una columna en la tabla
    private long usuario;

    // Métodos getter y setter para los atributos

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public long getMateria() {
        return materia;
    }

    public void setMateria(long materia) {
        this.materia = materia;
    }

    public long getUsuario() {
        return usuario;
    }

    public void setUsuario(long usuario) {
        this.usuario = usuario;
    }
}
