
package com.espe.dao;
//Importamos todas las librerias
import com.espe.model.Curso;

import java.util.List;

public interface ICursoDAO {
    // Método para guardar un curso en la BDD
    void guardar(Curso curso);

    // Método para editar la información de un curso en la BDD
    void editar(Curso curso);

    // Método para buscar un curso por su ID en BDD
    Curso buscar(int id);

    // Método para obtener una lista de todos los cursos en la BDD
    List<Curso> obtenerCursos();

    // Método para eliminar un curso por su ID de la BDD
    void eliminar(int id);
}
