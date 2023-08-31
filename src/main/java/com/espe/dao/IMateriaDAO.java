package com.espe.dao;

import com.espe.model.Materia;

import java.util.List;

public interface IMateriaDAO {
    // Método para guardar una materia en la base de datos
    void guardar(Materia materia);

    // Método para editar la información de una materia en la base de datos
    void editar(Materia materia);

    // Método para buscar una materia por su ID en la base de datos
    Materia buscar(int id);

    // Método para obtener una lista de todas las materias en la base de datos
    List<Materia> obtenerMaterias();

    // Método para obtener una lista de materias por curso y docente
    List<Materia> obtenerMateriasPorCurso(int curso, int docente);

    // Método para eliminar una materia por su ID de la base de datos
    void eliminar(int id);
}