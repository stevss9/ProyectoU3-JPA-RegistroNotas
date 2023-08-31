package com.espe.dao;

import com.espe.model.Curso;

import java.util.List;

public interface ICursoDAO {
    // Método para guardar un curso en la base de datos
    void guardar(Curso curso);

    // Método para editar la información de un curso en la base de datos
    void editar(Curso curso);

    // Método para buscar un curso por su ID en la base de datos
    Curso buscar(int id);

    // Método para obtener una lista de todos los cursos en la base de datos
    List<Curso> obtenerCursos();

    // Método para eliminar un curso por su ID de la base de datos
    void eliminar(int id);
}